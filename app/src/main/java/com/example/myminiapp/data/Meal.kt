package com.example.myminiapp.data

import com.google.gson.annotations.SerializedName

data class Meal(
    val meals: List<Dishes>
)

data class Dishes(
    @SerializedName("idMeal")
    val id: String,
    @SerializedName("strMeal")
    val name: String,
    @SerializedName("strMealThumb")
    val image: String?
)