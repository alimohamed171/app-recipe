package com.example.testrepo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.testrepo.network.APIClient
import com.example.testrepo.repo.MealRepository
import com.example.testrepo.user_data.UserDatabase
import com.example.testrepo.user_data.UserRepository
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

        gettingMealsViewModelReady()

        // View main random meal
        viewModel.getRandomMeal()
        viewModel.randomMeal.observe(viewLifecycleOwner) { meal ->
            val image: ImageView = view.findViewById(R.id.homeMainImg)
            val text: TextView = view.findViewById(R.id.MainMealName)
            Glide.with(this.requireActivity())
                .load(meal.strMealThumb)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loadin_image)
                        .error(R.drawable.broken_image))
                .into(image)
            text.text = meal.strMeal

            image.setOnClickListener {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                        meal.strMealThumb,
                        meal.strMeal,
                        meal.strInstructions,
                        meal.strYoutube,
                        meal.idMeal
                    )
                view.findNavController().navigate(action)
            }
        }

        // View home meals
        val recyclerView = view.findViewById<RecyclerView>(R.id.homeRecyclerView)
        var randomChar: Char
        do {
            randomChar = ('a'..'z').random()
        }
        while (randomChar == 'q' || randomChar == 'u' || randomChar == 'x' || randomChar == 'z')

        viewModel.getMealsByFirstLetter(randomChar)
        viewModel.listOfMeals.observe(viewLifecycleOwner) {
            recyclerView.adapter = HomeAdapter(it, this.requireActivity(), view)
        }
        recyclerView.layoutManager = LinearLayoutManager(this.requireActivity(), RecyclerView.HORIZONTAL, false)

    }

    private fun gettingMealsViewModelReady() {
        val userDao = UserDatabase.getDatabase(requireContext()).userDao()
        val mealDataDao = UserDatabase.getDatabase(requireContext()).mealDataDao()
        val favoriteDao = UserDatabase.getDatabase(requireContext()).favoritesDao()
        val mealsFactory = MealViewModelFactory(MealRepository(APIClient), UserRepository(userDao, favoriteDao, mealDataDao))
        viewModel = ViewModelProvider(this.requireActivity(), mealsFactory)[MealViewModel::class.java]
    }
}