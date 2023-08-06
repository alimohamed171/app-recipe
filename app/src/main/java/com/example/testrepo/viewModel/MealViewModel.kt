package com.example.testrepo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testrepo.model.Meal
import com.example.testrepo.repo.Repository
import kotlinx.coroutines.launch

class MealViewModel(val mealRepository: Repository): ViewModel() {
    private val _listOfMeals =MutableLiveData<List<Meal>>()
    val listOfMeals:LiveData<List<Meal>> = _listOfMeals

    private val _randomMeal = MutableLiveData<Meal>()
    val randomMeal: LiveData<Meal> = _randomMeal

    private val _resultMeals = MutableLiveData<List<Meal>>()
    val resultMeals: LiveData<List<Meal>> = _resultMeals

    fun getRandomMeal()
    {
        viewModelScope.launch {
            _randomMeal.value = mealRepository.getRemoteRandomMeal().meals[0]
        }
    }

    fun getMealByName(name: String)
    {
        viewModelScope.launch {
            _resultMeals.value = mealRepository.getMealByName(name).meals
        }
    }

    fun getMealsByFirstLetter(letter: Char)
    {
        viewModelScope.launch {
            _listOfMeals.value = mealRepository.getMealsByFirstLetter(letter).meals
        }
    }
}