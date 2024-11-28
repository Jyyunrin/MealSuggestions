package com.example.myminiapp.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
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

    suspend fun getMealById(id: String): Meal {
        val response = client.get(Endpoints.DISHBYID.url.format(id))

        val json = response.body<JsonObject>().toString()

        val gson = GsonBuilder().registerTypeAdapter(Dish::class.java, DishDeserializer())
            .registerTypeAdapter(Meal::class.java, MealDeserializer()).create()

        return gson.fromJson(json, Meal::class.java)
    }

    suspend fun getRandomMeal(): Meal {
        val response = client.get(Endpoints.RANDOMDISH.url)
        val json = response.body<JsonObject>().toString()
        val gson = GsonBuilder().registerTypeAdapter(Dish::class.java, DishDeserializer())
            .registerTypeAdapter(Meal::class.java, MealDeserializer()).create()

        return gson.fromJson(json, Meal::class.java)
    }
}