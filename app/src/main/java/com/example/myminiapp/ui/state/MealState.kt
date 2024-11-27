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
        Log.i("DEBUGGGGGGGGGSTATE", id)
        try {
            mealsIndividual.also {
                it.clear()
                it.addAll(mealRepository.getMealById(id).meals)
            }
        } catch (e: Exception) {
            Log.e("DEBUGGGGGGGGGSTATE", "Error fetching meal: ${e.message}", e)
        }
    }

    suspend fun getRandomDish() {
        meals.also{
            it.clear()
            it.addAll(mealRepository.getRandomMeal().meals)
        }
    }
}