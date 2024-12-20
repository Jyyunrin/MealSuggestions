package com.example.myminiapp.data

import com.google.gson.annotations.SerializedName

data class MealCategory(
    val categories: List<Category>
)


data class Category(
    @SerializedName("idCategory")
    val id: String,
    @SerializedName("strCategory")
    val name: String,
    @SerializedName("strCategoryThumb")
    val image: String?,
    @SerializedName("strCategoryDescription")
    val rawDescription: String?) {

    val description: String?
        get() = cleanDescription(rawDescription)

    private fun cleanDescription(description: String?): String? {
        return description?.replace(Regex("\\[\\d+]"), "")?.trim()
    }
}
