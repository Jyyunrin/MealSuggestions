package com.example.myminiapp.data

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class DishDeserializer : JsonDeserializer<Dish> {
    override fun deserialize(json: JsonElement,
                             typeOfT: Type,
                             context: JsonDeserializationContext): Dish{
        val jsonObject = json.asJsonObject

        val id = jsonObject.get("idMeal").asString
        val name = jsonObject.get("strMeal").asString
        val image = jsonObject.get("strMealThumb")?.asString
        val location = jsonObject.get("strArea")?.asString
        val instructions = jsonObject.get("strInstructions").asString

        val ingredients = mutableListOf<String>()
        for (i in 1..10) {
            val ingredient = jsonObject.get("strIngredient$i")?.asString
            if (!ingredient.isNullOrEmpty()) {
                ingredients.add(ingredient)
            }
        }

        return Dish(
            id = id,
            name = name,
            image = image,
            location = location,
            instructions = instructions,
            ingredients = ingredients
        )
    }
}
