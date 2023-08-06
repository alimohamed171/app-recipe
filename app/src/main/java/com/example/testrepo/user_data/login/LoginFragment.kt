package com.example.testrepo.user_data.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.testrepo.MainActivity2
import com.example.testrepo.R
import com.example.testrepo.user_data.User
import com.example.testrepo.user_data.UserDatabase
import com.example.testrepo.user_data.UserRepository
import com.example.testrepo.user_data.UserViewModel

class LoginFragment : Fragment() {
    private lateinit var btnlogin : Button
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var user: User
    private lateinit var EDTemail: EditText
    private lateinit var EDTpass : EditText

    ///
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_login, container, false)
//        sign up
        view.findViewById<Button>(R.id.txtSignUP).setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_loginFragment_to_registerFragment)
        }

        EDTemail = view.findViewById(R.id.logMail)
        EDTpass = view.findViewById(R.id.logPass)

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        val email = EDTemail.text
        val pass = EDTpass.text

        btnlogin = view.findViewById(R.id.btnSignUP)
        btnlogin.setOnClickListener{
//            val intent = Intent(activity, MainActivity2::class.java)
//            startActivity(intent)

//            user = mUserViewModel.getUser(email.toString(),pass.toString())
            mUserViewModel.getUser(email.toString())
            var userPass: String = mUserViewModel.getUserInfo()
//            user = mUserViewModel.getUserInfo()
//            Toast.makeText(requireContext(),"{${user.email} , ${user.password} , ${user.id} }" , Toast.LENGTH_LONG).show()
            Toast.makeText(requireContext(),"{${userPass} }" , Toast.LENGTH_LONG).show()
        }


        return view
    }



}