package com.example.testrepo.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testrepo.repo.MealRepository
import com.example.testrepo.user_data.UserRepository
import java.lang.IllegalArgumentException

class MealViewModelFactory(val mealRepository: MealRepository, val userRepository: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MealViewModel::class.java))
        {
            return MealViewModel(mealRepository, userRepository) as T
        }
        else
        {
            throw IllegalArgumentException("Type of Model Class is Not Supported")
        }
    }
}