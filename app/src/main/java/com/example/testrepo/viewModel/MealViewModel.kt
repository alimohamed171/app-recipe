package com.example.testrepo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testrepo.Meal
import com.example.testrepo.repo.Repository
import kotlinx.coroutines.launch

class MealViewModel(val mealRepository: Repository): ViewModel() {
    private val _listOfMeals =MutableLiveData<List<Meal>>()
    val listOfMeals:LiveData<List<Meal>> = _listOfMeals

    fun getRandomMeal()
    {
        viewModelScope.launch {
            _listOfMeals.value = mealRepository.getRemoteRandomMeal().meals
        }
    }

    fun getMealByName(name: String)
    {
        viewModelScope.launch {
            _listOfMeals.value = mealRepository.getMealByName(name).meals
        }
    }

    fun getMealsByFirstLetter(letter: Char)
    {
        viewModelScope.launch {
            _listOfMeals.value = mealRepository.getMealsByFirstLetter(letter).meals
        }
    }
}