package com.example.myminiapp

import android.app.Application
import com.example.myminiapp.data.MealRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.gson.gson

class MyApp: Application() {
    private val client = HttpClient {
        install(ContentNegotiation){
            gson()
        }
    }

    val mealRepository by lazy {
        MealRepository(client);
    }
}