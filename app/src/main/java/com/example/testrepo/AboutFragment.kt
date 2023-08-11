package com.example.testrepo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AboutFragment : Fragment() {
  val team : List<TeamData> = arrayListOf(
TeamData("Ali Mohamed","https://www.linkedin.com/in/ali-mohamed-3539a2234","ali mohamed","https://github.com/alimohamed171","alimohamed171",R.drawable.chef),
TeamData("Mohamed Maged","https://www.linkedin.com/in/mohamed-maged-4a3b761b5","mohamed maged","https://github.com/Ibn-Maged","Ibn-Maged",R.drawable.coding),
TeamData("Sara Almohands","https://www.linkedin.com/in/sara-osama-almohands/","sara osama almohands","https://github.com/Sara-Almohands","Sara-Almohands",R.drawable.girl_chef),
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
        var adapter = TeamDataAdabter(team)
        recycler.adapter=adapter
        recycler.layoutManager= LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)


    }


}