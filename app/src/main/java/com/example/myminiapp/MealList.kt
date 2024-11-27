package com.example.myminiapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.myminiapp.data.Dish
import com.example.myminiapp.data.MealRepository
import com.example.myminiapp.ui.state.MealCategoryState
import com.example.myminiapp.ui.state.MealState

@Composable
fun MealList(navController: NavController, mealRepository:MealRepository, category: String?) {
    val mealState = MealState(mealRepository)

    LaunchedEffect(mealState) {
        mealState.getMeals(category?:"")
    }

    LazyColumn {
        items(mealState.meals.size) {
            MealCard(navController, mealState.meals[it])
        }
    }
}

@Composable
fun MealCard(navController: NavController, meal: Dish) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0x7E3CC034)),
        modifier= Modifier.fillMaxWidth()
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(meal.name, fontSize = 25.sp)
            AsyncImage(
                model = meal.image, contentDescription = null,
                modifier = Modifier.size(250.dp).padding(20.dp)
            )
            Button(onClick = {navController.navigate("dish/${meal.id}")}){
                Text("See Recipe")
            }
        }
    }
}
