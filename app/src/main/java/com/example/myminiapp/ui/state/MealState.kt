package com.example.myminiapp.ui.state

import androidx.compose.runtime.mutableStateListOf
import com.example.myminiapp.data.Dishes
import com.example.myminiapp.data.MealRepository

class MealState(private val mealRepository: MealRepository) {

    var meals = mutableStateListOf<Dishes>();

    suspend fun getMeals(category: String) {
        meals.also{
            it.clear()
            it.addAll(mealRepository.getMealsByCategory(category).meals)
        }
    }
}