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
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testrepo.network.APIClient
import com.example.testrepo.repo.MealRepository
import com.example.testrepo.viewModel.MealViewModel
import com.example.testrepo.viewModel.MealViewModelFactory


class SearchFragment : Fragment() {
    private lateinit var viewModel: MealViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        val mealsFactory = MealViewModelFactory(MealRepository(APIClient))
        viewModel = ViewModelProvider(this.requireActivity(), mealsFactory)[MealViewModel::class.java]
    }

    private fun searchQuery(query: String, view: View) {
        gettingMealsViewModelReady()
        val recyclerView = view.findViewById<RecyclerView>(R.id.searchRecyclerView)
        viewModel.getMealByName(query)
        viewModel.listOfMeals.observe(viewLifecycleOwner) {
            recyclerView.adapter = SearchAdapter(it, this.requireActivity(), view)
        }
        recyclerView.layoutManager = LinearLayoutManager(this.requireActivity(), RecyclerView.VERTICAL, false)
    }
}
