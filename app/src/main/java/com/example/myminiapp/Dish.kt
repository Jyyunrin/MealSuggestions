package com.example.myminiapp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.myminiapp.data.Dish
import com.example.myminiapp.data.MealRepository
import com.example.myminiapp.ui.state.MealState

@Composable
fun Dish(mealRepository: MealRepository, id: String?) {
    val mealState = MealState(mealRepository)

    Log.i("DEBUGGGGGGG", "$id")



    LaunchedEffect(id) {
        if (!id.isNullOrEmpty()) {
            mealState.getDish(id)
        } else {
            mealState.getRandomDish()
        }
    }


    val bools = mealState.mealsIndividual.isEmpty();
    Log.i("DEBUGGGGGGGGGG", "True or false $bools")

    LazyColumn {
        items(mealState.mealsIndividual.size) {
            DishPage(mealState.mealsIndividual[it])
        }
    }
}

    @Composable
    fun DishPage(dish: Dish) {
        Box(modifier = Modifier.fillMaxSize().background(Color(0xFFFF965B),)) {
                Column {
                    Text(dish.name, fontSize = 20.sp)
                    AsyncImage(
                        model = dish.image,
                        contentDescription = null,
                        modifier = Modifier.size(250.dp)
                    )
                }

    }
}