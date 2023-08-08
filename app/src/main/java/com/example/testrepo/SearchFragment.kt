package com.example.testrepo

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.SearchView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testrepo.network.APIClient
import com.example.testrepo.repo.MealRepository
import com.example.testrepo.user_data.User
import com.example.testrepo.user_data.UserDatabase
import com.example.testrepo.user_data.UserRepository
import com.example.testrepo.viewModel.MealViewModel
import com.example.testrepo.viewModel.MealViewModelFactory


class SearchFragment : Fragment() {
    private lateinit var viewModel: MealViewModel
    private lateinit var searchErrorText: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchErrorText = view.findViewById(R.id.search_error_text)
        searchErrorText.visibility = View.GONE

        val searchView: SearchView = view.findViewById(R.id.searchView)
        showSoftKeyboard(searchView)

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                if (query != null) {
                    searchQuery(query, view)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val inputMethodManager: InputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    private fun gettingMealsViewModelReady() {
        val userDao = UserDatabase.getDatabase(requireContext()).userDao()
        val mealDataDao = UserDatabase.getDatabase(requireContext()).mealDataDao()
        val favoriteDao = UserDatabase.getDatabase(requireContext()).favoritesDao()
        val mealsFactory = MealViewModelFactory(MealRepository(APIClient), UserRepository(userDao, favoriteDao, mealDataDao))
        viewModel = ViewModelProvider(this.requireActivity(), mealsFactory)[MealViewModel::class.java]
    }

    private fun searchQuery(query: String, view: View) {
        gettingMealsViewModelReady()
        val recyclerView = view.findViewById<RecyclerView>(R.id.searchRecyclerView)
        viewModel.getMealByName(query)
        viewModel.resultMeals.observe(viewLifecycleOwner) {
            if(it != null)
            {
                recyclerView.adapter = SearchAdapter(it, this.requireActivity(), view)
                searchErrorText.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
            else
            {
                view.findViewById<TextView>(R.id.search_error_text).setText("No Results Found.")
                searchErrorText.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this.requireActivity(), RecyclerView.VERTICAL, false)
    }
}
