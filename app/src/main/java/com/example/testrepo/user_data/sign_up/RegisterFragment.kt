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
import com.example.testrepo.SharedPrefs
import com.example.testrepo.user_data.User
import com.example.testrepo.user_data.UserViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
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
        //padding between text and icon
        EDTemail.compoundDrawablePadding = 30
        EDTpass.compoundDrawablePadding = 30
        EDTphone.compoundDrawablePadding = 30


        var email: String
        var pass: String
        var phone: String
        //val paddingInPixels: Int = resources.getDimensionPixelSize(R.dimen.padding_size)



        btnSignUP.setOnClickListener{
            email = EDTemail.text.toString()
            pass = EDTpass.text.toString()
            phone = EDTphone.text.toString()

            if( TextUtils.isEmpty(email) ){
                errorSnackBar(view,"Email field is empty please enter your email")
            }else if(TextUtils.isEmpty(pass)){
                errorSnackBar(view,"Password field is empty please enter your Password")
            }else if(TextUtils.isEmpty(phone)){
                errorSnackBar(view,"Phone field is empty please enter your Phone")
            } else if(!validateEmail(email)){
                errorSnackBar(view,"Email is nat valid ")
            }
            else if(!validatePassword(pass)){
                errorSnackBar(view,"Password is nat valid ")
            }else if (!validatePhone(phone)){
                errorSnackBar(view,"Phone is nat valid ")
            } else {
                lifecycleScope.launch {
                    // first get this user from database if exist
                    val user: User? = mUserViewModel.getUser(email)
                    if (user == null) {
                        val currentUser = User(0, email, pass, phone)
                        mUserViewModel.addUser(currentUser)

                        delay(10)
                        lifecycleScope.launch {
                            val testID :User? = mUserViewModel.getUser(email)
                            if (testID!=null){
                               // Toast.makeText(requireContext(), "${testID.id}", Toast.LENGTH_LONG).show()
                                SharedPrefs.signIn(testID.id)
                                val intent = Intent(activity, MainActivity2::class.java)
                                startActivity(intent)
                            }else{
                                errorSnackBar(view,"error in SIGNUP")
                            }
                        }
                    } else {
                        errorDialog("This user email is already exist ")
                    }
                }// end of launch
            }// end of else
                    EDTemail.text.clear()
                    EDTpass.text.clear()
                    EDTphone.text.clear()
        }// end of button click


    }//end of on view


    private fun errorDialog(errorMessage:String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("OK"){_,_->}
        builder.setTitle("EROR")
        builder.setMessage(errorMessage)
        builder.create().show()
    }
    private fun errorSnackBar(view: View,errorMessage:String) {
        Snackbar.make(view,errorMessage, Snackbar.LENGTH_LONG)
            .show()
    }

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


}