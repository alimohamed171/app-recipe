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
        viewModel.listOfMeals.observe(viewLifecycleOwner) { meals ->
            val image: ImageView = view.findViewById(R.id.homeMainImg)
            val text: TextView = view.findViewById(R.id.MainMealName)
            Glide.with(this.requireActivity())
                .load(meals[0].strMealThumb)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loadin_image)
                        .error(R.drawable.broken_image))
                .into(image)
            text.text = meals[0].strMeal

            image.setOnClickListener {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                        meals[0].strMealThumb,
                        meals[0].strMeal,
                        meals[0].strInstructions,
                        meals[0].strYoutube)
                view.findNavController().navigate(action)
            }
        }

        // View home meals
        val recyclerView = view.findViewById<RecyclerView>(R.id.homeRecyclerView)
        viewModel.getMealsByFirstLetter(('a'..'z').random())
        viewModel.listOfMeals.observe(viewLifecycleOwner) {
            recyclerView.adapter = HomeAdapter(it, this.requireActivity())
        }
        recyclerView.layoutManager = LinearLayoutManager(this.requireActivity(), RecyclerView.HORIZONTAL, false)


    }

    private fun gettingMealsViewModelReady() {
        val mealsFactory = MealViewModelFactory(MealRepository(APIClient))
        viewModel = ViewModelProvider(this.requireActivity(), mealsFactory)[MealViewModel::class.java]
    }
}