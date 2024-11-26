package com.example.myminiapp.data

import com.google.gson.Gson
import com.google.gson.JsonObject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MealRepository(private val client: HttpClient) {

    suspend fun getMealsByCategory(category: String): Meal {
        val response = client.get(Endpoints.MEALSBYCATEGORY.url.format(category))

        val json = response.body<JsonObject>().toString()

        return Gson().fromJson(json, Meal::class.java)
    }

    suspend fun getCategory(): MealCategory {
        val response = client.get(Endpoints.CATEGORIES.url)

        val json = response.body<JsonObject>().toString()

        return Gson().fromJson(json, MealCategory::class.java)
    }
}