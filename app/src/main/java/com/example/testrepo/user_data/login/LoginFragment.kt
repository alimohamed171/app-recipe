package com.example.testrepo.user_data.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.testrepo.R
import com.example.testrepo.user_data.User
import com.example.testrepo.user_data.UserViewModel
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private lateinit var btnlogin : Button
    private lateinit var mUserViewModel: UserViewModel
   // private lateinit var user: User
    private lateinit var EDTemail: EditText
    private lateinit var EDTpass : EditText


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

        btnlogin = view.findViewById(R.id.btnLogin)
        EDTemail = view.findViewById(R.id.logMail)
        EDTpass = view.findViewById(R.id.logPass)

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

       // mUserViewModel.getUser("aa")
        //mUserViewModel.getUser(email)
      //  val test :User = User(0,"moh","11","11")
       // mUserViewModel.addUser(test)

        btnlogin.setOnClickListener{
//            val intent = Intent(activity, MainActivity2::class.java)
//            startActivity(intent)
            val email = EDTemail.text.toString()
            val pass = EDTpass.text.toString()

            lifecycleScope.launch {
             val user = mUserViewModel.getUser(email)
                if (user != null){
                    Toast.makeText(requireContext(),"finalllllly }" , Toast.LENGTH_LONG).show()
                    Toast.makeText(requireContext(),"{${user.id} , ${user.email} }" , Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(requireContext(), "${mUserViewModel.user.value} fuck ali }", Toast.LENGTH_LONG).show()
                }
            }


//            mUserViewModel.user.observe(viewLifecycleOwner){result ->
//                if (result!=null){
//
//                    if (result.password == pass){
//                        Toast.makeText(requireContext(),"finalllllly }" , Toast.LENGTH_LONG).show()
//                    }else{
//                        Toast.makeText(requireContext(),"fuck ali }" , Toast.LENGTH_LONG).show()
//                    }
//                }else{
//                    Toast.makeText(requireContext(), "${mUserViewModel.user.value} fuck ali }", Toast.LENGTH_LONG).show()
//                }
//            }

//            Toast.makeText(requireContext(),"{${user.email} , ${user.password} , ${user.id} }" , Toast.LENGTH_LONG).show()
//            Toast.makeText(requireContext(),"{${userPass} }" , Toast.LENGTH_LONG).show()
        }


        return view
    }



}