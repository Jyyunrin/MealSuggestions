package com.example.myminiapp.ui.state

import androidx.compose.runtime.mutableStateListOf
import com.example.myminiapp.data.Category
import com.example.myminiapp.data.MealRepository

class MealCategoryState(private val mealRepository: MealRepository) {
    var mealCategories = mutableStateListOf<Category>()

    suspend fun getCategories() {
        mealCategories.also{
           it.clear();
           it.addAll(mealRepository.getCategory().categories)
        }
    }
}