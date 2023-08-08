package com.example.testrepo

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
import com.example.testrepo.user_data.UserDatabase
import com.example.testrepo.user_data.UserRepository
import com.example.testrepo.viewModel.MealViewModel
import com.example.testrepo.viewModel.MealViewModelFactory


class FavoriteFragment : Fragment() {
    private var mealViewModel: MealViewModel? = null
    private lateinit var placeholderText: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        if(mealViewModel == null)
        {
            createMealViewModel()
        }

        placeholderText = view.findViewById(R.id.favorite_placeholder_text)
        val recyclerView: RecyclerView = view.findViewById(R.id.searchRecyclerView)
        val favoriteAdapter = FavoriteAdapter(this.requireActivity(), view)
        recyclerView.adapter = favoriteAdapter
        mealViewModel?.getFavoriteMeals(SharedPrefs.getCurrentUser())
        mealViewModel?.favoriteMeals?.observe(viewLifecycleOwner){
            favoriteAdapter.setData(it)
            if(it.size != 0)
            {
                placeholderText.visibility = View.GONE
            }
            else
            {
                placeholderText.visibility = View.VISIBLE
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this.context)
    }

    private fun createMealViewModel()
    {
        val userDao = UserDatabase.getDatabase(requireContext()).userDao()
        val mealDataDao = UserDatabase.getDatabase(requireContext()).mealDataDao()
        val favoriteDao = UserDatabase.getDatabase(requireContext()).favoritesDao()
        val mealViewModelFactory = MealViewModelFactory(MealRepository(APIClient), UserRepository(userDao, favoriteDao, mealDataDao))
        mealViewModel = ViewModelProvider(this.requireActivity(), mealViewModelFactory).get(MealViewModel::class.java)
    }
}