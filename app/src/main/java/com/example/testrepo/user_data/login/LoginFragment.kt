package com.example.testrepo.user_data.login

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Message
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.testrepo.MainActivity2
import com.example.testrepo.R
import com.example.testrepo.SharedPrefs
import com.example.testrepo.user_data.User
import com.example.testrepo.user_data.UserViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private lateinit var btnlogin : Button
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var EDTemail: EditText
    private lateinit var EDTpass : EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_login, container, false)
//        sign up
        view.findViewById<Button>(R.id.txtSignUP).setOnClickListener {
            goToRegister(view)
        }
 view.findViewById<TextView>(R.id.forgotPass).setOnClickListener {
          errorDialog("Forgot Password","we here in recipe app not facebook wait until we add this feature or go and make a new account")
        }

        btnlogin = view.findViewById(R.id.btnLogin)
        EDTemail = view.findViewById(R.id.logMail)
        EDTpass = view.findViewById(R.id.logPass)
        //padding between text and icon
        EDTemail.compoundDrawablePadding = 30
        EDTpass.compoundDrawablePadding = 30

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

//        val test :User = User(0,"moh","11","11")
//        mUserViewModel.addUser(test)
//        val test1 :User = User(0,"ali","123","123")
//        mUserViewModel.addUser(test1)
//        val test2 :User = User(0,"sara","123","123")
//        mUserViewModel.addUser(test2)

        btnlogin.setOnClickListener{

            val email = EDTemail.text.toString()
            val pass = EDTpass.text.toString()

            if( TextUtils.isEmpty(email) ){
               // errorDialog("Email field is empty please enter your email")
                errorSnackBar(view,"Email field is empty please enter your email",pass)
            }else if(TextUtils.isEmpty(pass)){
                errorSnackBar(view,"Password field is empty please enter your Password",pass)
            }
            // check here for regex
            else{
                lifecycleScope.launch {
                    val user : User? = mUserViewModel.getUser(email)
                    if (user != null){
                        if(pass == user.password){
//                            errorDialog("{${user.id} , ${user.email}, ${user.password} , ${user.phone} }")
                            SharedPrefs.signIn(user.id)
                            val intent = Intent(activity, MainActivity2::class.java)
                            startActivity(intent)
                        }else{
                            errorSnackBar(view,"password Invalid",user.password)

                        }

                    }else {
                        errorDialogToRegister(view)

                    }//end of if user null

                }// end of launch

            }//end of else  to get user

        }//end of Click Listener

        return view
    }

    private fun goToRegister(view: View) {
        Navigation.findNavController(view)
            .navigate(R.id.action_loginFragment_to_registerFragment)
    }

    private fun errorDialog(title:String,message: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("OK") { _, _ -> }
        builder.setTitle(title)
        builder.setMessage(message)
        builder.create().show()
    }
    private fun errorSnackBar(view: View,errorMessage:String,pass:String) {
        Snackbar.make(view,errorMessage,Snackbar.LENGTH_LONG)
            .setAction("OK"){
                errorDialog("password","your password is \" $pass \" \nBe honest don't use it to find out users' passwords ")
            }
        .show()
    }
    private fun errorDialogToRegister( view: View) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Sign up"){_,_->
            goToRegister(view)
            EDTemail.text.clear()
            EDTpass.text.clear()
        }
        builder.setNegativeButton("stay"){_,_ -> }
        builder.setTitle("ERROR")
        builder.setMessage("Can't found this email please Sign up first")
        builder.create().show()

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