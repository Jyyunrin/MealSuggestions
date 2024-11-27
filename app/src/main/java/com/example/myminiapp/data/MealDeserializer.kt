package com.example.myminiapp.data

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class MealDeserializer : JsonDeserializer<Meal> {
    override fun deserialize(json: JsonElement,
                             typeOfT: Type,
                             context: JsonDeserializationContext
    ): Meal {
        val jsonObject = json.asJsonObject

        val mealsJsonArray = jsonObject.getAsJsonArray("meals")
        val meals = mealsJsonArray.map {
            context.deserialize<Dish>(it, Dish::class.java)
        }

        return Meal(meals = meals)
    }
}