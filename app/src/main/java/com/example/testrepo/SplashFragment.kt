package com.example.testrepo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        lifecycleScope.launch (Dispatchers.Main) {
            delay(5000)
            if(SharedPrefs.getCurrentUser() == -1)
            {
                Navigation.findNavController(view)
                    .navigate(R.id.action_splashFragment_to_loginFragment)
            }
            else
            {
                val intent = Intent(activity, MainActivity2::class.java)
                startActivity(intent)
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }


}