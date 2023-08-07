package com.example.testrepo.user_data.sign_up

import android.app.AlertDialog
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
import androidx.lifecycle.lifecycleScope
import com.example.testrepo.MainActivity2
import com.example.testrepo.R
import com.example.testrepo.user_data.User
import com.example.testrepo.user_data.UserViewModel
import kotlinx.coroutines.launch

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

        var email: String
        var pass: String
        var phone: String

        btnSignUP.setOnClickListener{
            email = EDTemail.text.toString()
            pass = EDTpass.text.toString()
            phone = EDTphone.text.toString()

            if( TextUtils.isEmpty(email) ){
                errorDialog("Email field is empty please enter your email")
            }else if(TextUtils.isEmpty(pass)){
                errorDialog("Password field is empty please enter your Password")
            }else if(TextUtils.isEmpty(phone)){
                errorDialog("Phone field is empty please enter your Phone")
            }
               // if(validateEmail(email) && validatePassword(pass) && validatePhone(phone)) {
                lifecycleScope.launch {
                    // first get this user from database if exist
                    val user: User? = mUserViewModel.getUser(email)
                    if (user == null) {
                        val currentUser = User(0, email, pass, phone)
                        mUserViewModel.addUser(currentUser)
                        Toast.makeText(requireContext(),"Successfully Signed Up" , Toast.LENGTH_LONG).show()
//                        val intent = Intent(activity, MainActivity2::class.java)
//                        startActivity(intent)

                    } else {
                        errorDialog("This user email is already exist ")
                    }
                }// end of launch

//                    SharedPrefs.signIn()
                    EDTemail.text.clear()
                    EDTpass.text.clear()
                    EDTphone.text.clear()

              //  } else {
              //      Toast.makeText(context, "One or More of Your Inputs is Invalid", Toast.LENGTH_SHORT).show()
              //  }

        }// end of button click


    }//end of on view


    private fun errorDialog(errorMessage:String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("OK"){_,_->}
        builder.setTitle("EROR")
        builder.setMessage(errorMessage)
        builder.create().show()
    }

    private fun inputCheck(email: String, pass: String, phone: String): Boolean {
        return !(TextUtils.isEmpty(email) && TextUtils.isEmpty(pass) && TextUtils.isEmpty(phone)  )
    }
/*
    private fun validateEmail(email: String): Boolean
    {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}"
        val regex = Regex(emailPattern)
        return regex.matches(email)
    }

    private fun validatePassword(password: String): Boolean
    {
        val strongPasswordPattern = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&_#])[A-Za-z\\d@\$!%*?&_#]{8,}"
        val regex = Regex(strongPasswordPattern)
        return regex.matches(password)
    }

    private fun validatePhone(phone: String): Boolean
    {
        val phonePattern = "01[\\d]{9,}"
        val regex = Regex(phonePattern)
        return regex.matches(phone)
    }

   */
}