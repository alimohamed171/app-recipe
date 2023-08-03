package com.example.testrepo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    private fun onCheckedFavouriteButton() {
//        checkBox.setOnCheckedChangeListener(checkBox, isChecked ->
//        if (isChecked)
//            TODO()
//        else
//            TODO()
//        )
    }
}