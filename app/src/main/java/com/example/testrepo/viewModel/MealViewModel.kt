package com.example.testrepo.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testrepo.model.Meal
import com.example.testrepo.repo.MealRepository
import com.example.testrepo.repo.Repository
import com.example.testrepo.user_data.Favorite
import com.example.testrepo.user_data.MealData
import com.example.testrepo.user_data.UserRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MealViewModel(val mealRepository: Repository, val userRepository: UserRepository): ViewModel() {
    private val _listOfMeals =MutableLiveData<List<Meal>>()
    val listOfMeals:LiveData<List<Meal>> = _listOfMeals

    private val _randomMeal = MutableLiveData<Meal>()
    val randomMeal: LiveData<Meal> = _randomMeal

    private val _resultMeals = MutableLiveData<List<Meal>>()
    val resultMeals: LiveData<List<Meal>> = _resultMeals

    private val _favoriteMeals = MutableLiveData<List<Meal>>()
    val favoriteMeals: LiveData<List<Meal>> = _favoriteMeals

    private var favorites: MutableList<String> = mutableListOf()
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

    fun insertMeal(mealId: String, mealName: String)
    {
        viewModelScope.launch {
            userRepository.insertMeal(mealId, mealName)
        }
    }

    fun addToFavorites(mealId: String, userId: Int)
    {
        viewModelScope.launch {
            userRepository.addToFavorites(mealId, userId)
        }
    }
    fun removeFromFavorites(mealId: String, userId: Int)
    {
        favorites.remove(mealId)
        viewModelScope.launch {
            userRepository.removeFromFavorites(mealId, userId)
        }
    }
    fun getFavoriteMeals(userId: Int)
    {
        var tempFavorites: MutableList<String>
        viewModelScope.launch {
            tempFavorites = userRepository.getFavorites(userId)
            if(tempFavorites != favorites)
            {
                favorites = tempFavorites
                val listOfMeals: MutableList<Meal> = mutableListOf()
                for (mealId in favorites)
                {
                    listOfMeals.add(mealRepository.getMealById(mealId).meals[0])
                }
                _favoriteMeals.value = listOfMeals
            }
        }
    }

    fun isFavorite(mealId: String, userId: Int): Favorite
    {
        var favorite: Favorite
        runBlocking {
            favorite = userRepository.isFavorite(mealId, userId)
        }
        return favorite
    }
}