package com.example.myminiapp.data

import com.google.gson.annotations.SerializedName

data class Meal(
    val meals: List<Dish>
)

data class Dish(
    @SerializedName("idMeal")
    val id: String,
    @SerializedName("strMeal")
    val name: String,
    @SerializedName("strMealThumb")
    val image: String?,
    @SerializedName("strArea")
    val location: String?,
    @SerializedName("strInstructions")
    val instructions: String,
    val ingredients: List<String>
)