package com.example.testrepo.user_data.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.testrepo.MainActivity2
import com.example.testrepo.R

class LoginFragment : Fragment() {
    private lateinit var btnlogin : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_login, container, false)

        view.findViewById<TextView>(R.id.txtSignUP).setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_loginFragment_to_registerFragment)
        }

        btnlogin = view.findViewById(R.id.btnSignUP)
        btnlogin.setOnClickListener{
            val intent = Intent(activity, MainActivity2::class.java)
            startActivity(intent)
        }


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        view.findViewById<TextView>(R.id.txtSignUP).setOnClickListener {
//            Navigation.findNavController(view)
//                .navigate(R.id.action_loginFragment_to_registerFragment)
//        }
//
//           btnlogin = view.findViewById(R.id.btnSignUP)
//           btnlogin.setOnClickListener{
//          val intent = Intent(activity, MainActivity2::class.java)
//          startActivity(intent)
//          }
    }

}