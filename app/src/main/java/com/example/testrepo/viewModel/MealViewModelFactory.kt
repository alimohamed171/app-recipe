package com.example.testrepo.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testrepo.repo.MealRepository
import java.lang.IllegalArgumentException

class MealViewModelFactory(val mealRepository: MealRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MealViewModel::class.java))
        {
            return MealViewModel(mealRepository) as T
        }
        else
        {
            throw IllegalArgumentException("Type of Model Class is Not Supported")
        }
    }
}