package com.example.testrepo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity


class AboutFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

//    override fun onResume() {
//        super.onResume()
//        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        (requireActivity() as AppCompatActivity).supportActionBar?.show()
//    }

}