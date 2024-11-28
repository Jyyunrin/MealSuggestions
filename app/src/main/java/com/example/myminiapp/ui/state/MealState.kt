package com.example.myminiapp.ui.state

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.example.myminiapp.data.Dish
import com.example.myminiapp.data.MealRepository

class MealState(private val mealRepository: MealRepository) {

    var meals = mutableStateListOf<Dish>();

    var mealsIndividual = mutableStateListOf<Dish>();

    suspend fun getMeals(category: String) {
        meals.also{
            it.clear()
            it.addAll(mealRepository.getMealsByCategory(category).meals)
        }
    }

    suspend fun getDish(id: String) {
        mealsIndividual.also {
            it.clear()
            it.addAll(mealRepository.getMealById(id).meals)
        }
    }

    suspend fun getRandomDish() {
        mealsIndividual.also{
            it.clear()
            it.addAll(mealRepository.getRandomMeal().meals)
        }
    }
}
