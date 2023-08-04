package com.example.testrepo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testrepo.network.APIClient
import com.example.testrepo.repo.MealRepository
import com.example.testrepo.viewModel.MealViewModel
import com.example.testrepo.viewModel.MealViewModelFactory

class HomeFragment : Fragment() {
    private lateinit var viewModel: MealViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.homeRecyclerView)

        gettingProductsViewModelReady()
        viewModel.getMealsByFirstLetter(('a'..'z').random())
        viewModel.listOfMeals.observe(viewLifecycleOwner) {
            recyclerView.adapter = HomeAdapter(it, this.requireActivity())
        }
        recyclerView.layoutManager = LinearLayoutManager(this.requireActivity(), RecyclerView.HORIZONTAL, false)
    }

    private fun gettingProductsViewModelReady() {
        val mealsFactory = MealViewModelFactory(MealRepository(APIClient))
        viewModel = ViewModelProvider(this.requireActivity(), mealsFactory)[MealViewModel::class.java]
    }
}