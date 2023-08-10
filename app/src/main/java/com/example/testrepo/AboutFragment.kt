package com.example.testrepo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//
//TeamData("Ali Mohamed","https://www.linkedin.com/in/ali-mohamed-3539a2234","https://github.com/alimohamed171",R.drawable.app_menu_icon),
//TeamData("Mohamed Maged","https://www.linkedin.com/in/mohamed-maged-4a3b761b5","https://github.com/Ibn-Maged",R.drawable.app_menu_icon),
//)
class AboutFragment : Fragment() {
  val team : List<TeamData> = arrayListOf(
TeamData("Ali Mohamed","https://www.linkedin.com/in/ali-mohamed-3539a2234","https://linkedin.com/ali-mohamed","https://github.com/alimohamed171",R.drawable.chef),
TeamData("Mohamed Maged","https://www.linkedin.com/in/mohamed-maged-4a3b761b5","https://linkedin.com/mohamed-maged","https://github.com/Ibn-Maged",R.drawable.coding),
TeamData("Sara Almohands","https://www.linkedin.com/in/mohamed-maged-4a3b761b5","https://www.linkedin.com/in/sara-osama-almohands/","https://github.com/Sara-Almohands",R.drawable.girl_chef),
TeamData("Ali Mohamed","https://www.linkedin.com/in/ali-mohamed-3539a2234","https://linkedin.com/ali-mohamed","https://github.com/alimohamed171",R.drawable.hamburger),
TeamData("Mohamed Maged","https://www.linkedin.com/in/mohamed-maged-4a3b761b5","https://linkedin.com/mohamed-maged","https://github.com/Ibn-Maged",R.drawable.pizza),
TeamData("Sara Almohands","https://www.linkedin.com/in/mohamed-maged-4a3b761b5","https://www.linkedin.com/in/sara-osama-almohands/","https://github.com/Sara-Almohands",R.drawable.shawarma),
  )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler: RecyclerView = view.findViewById(R.id.revi)
        var adabter = TeamDataAdabter(team)
        recycler.adapter=adabter
        recycler.layoutManager= LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)


    }


}