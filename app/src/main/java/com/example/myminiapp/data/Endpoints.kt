package com.example.myminiapp.data

enum class Endpoints(val url:String) {

    BASE_URL("https://themealdb.com/api/json/v1/1"),
    CATEGORIES("${BASE_URL.url}/categories.php"),
    MEALSBYCATEGORY("${BASE_URL.url}/filter.php?c=%s");

    fun format(str:String?): String {
        if (str == null) return ""
        return url.format(str);
    }

}