package com.example.testrepo

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testrepo.network.APIClient
import com.example.testrepo.repo.MealRepository
import com.example.testrepo.viewModel.MealViewModel
import com.example.testrepo.viewModel.MealViewModelFactory


class FavoriteFragment : Fragment() {
    private var mealViewModel: MealViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(mealViewModel == null)
        {
            createMealViewModel()
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.searchRecyclerView)
        val favoriteAdapter = FavoriteAdapter(this.requireActivity(), view)
        recyclerView.adapter = favoriteAdapter
        mealViewModel?.favoriteMeals?.observe(viewLifecycleOwner){
            favoriteAdapter.setData(it)
            view.findViewById<TextView>(R.id.favorite_placeholder_text).visibility = View.GONE
        }
        recyclerView.layoutManager = LinearLayoutManager(this.context)
    }

    private fun createMealViewModel()
    {
        val mealViewModelFactory = MealViewModelFactory(MealRepository(APIClient))
        mealViewModel = ViewModelProvider(this.requireActivity(), mealViewModelFactory).get(MealViewModel::class.java)
    }
}