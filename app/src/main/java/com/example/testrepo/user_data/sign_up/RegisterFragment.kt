package com.example.testrepo.user_data.sign_up

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.testrepo.MainActivity2
import com.example.testrepo.R
import com.example.testrepo.user_data.User
import com.example.testrepo.user_data.UserViewModel

class RegisterFragment : Fragment() {
    private lateinit var btnSignUP : Button
    private lateinit var EDTemail: EditText
    private lateinit var EDTpass : EditText
    private lateinit var EDTphone: EditText
    private lateinit var mUserViewModel:UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        // initialize var
        btnSignUP = view.findViewById(R.id.btnSignUP)
       EDTemail = view.findViewById(R.id.edtMail)
        EDTpass = view.findViewById(R.id.edtPass)
        EDTphone = view.findViewById(R.id.edtPhone)

        val email = EDTemail.text
        val pass = EDTpass.text
        val phone = EDTphone.text

        btnSignUP.setOnClickListener{

            insertDataToDataBase(email.toString(),pass.toString(),phone.toString())

        }
    }

    private fun insertDataToDataBase(email: String, pass: String, phone: String) {

        if(inputCheck(email,pass,phone)){
            // addUser take user so we need to create user object
            val user = User(0,email,pass,phone)
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"Successfully sign up" , Toast.LENGTH_LONG).show()
          //  findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

            val intent = Intent(activity, MainActivity2::class.java)
            startActivity(intent)

        }else{
            Toast.makeText(requireContext(),"$email $pass $phone " , Toast.LENGTH_LONG).show()
            Toast.makeText(requireContext(),"please insert all the data" , Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(email: String, pass: String, phone: String): Boolean {
        return !(TextUtils.isEmpty(email) && TextUtils.isEmpty(pass) && TextUtils.isEmpty(phone)  )
    }


}