package com.example.myminiapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.myminiapp.data.Dish
import com.example.myminiapp.data.MealRepository
import com.example.myminiapp.ui.state.MealState

@Composable
fun MealList(navController: NavController, mealRepository:MealRepository, category: String?) {
    val mealState = remember{MealState(mealRepository)}

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
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEB3B)),
        modifier= Modifier.fillMaxWidth()
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(meal.name, fontSize = 35.sp, fontWeight= FontWeight.ExtraBold,
                textAlign= TextAlign.Center)
            AsyncImage(
                model = meal.image, contentDescription = null,
                modifier = Modifier.size(300.dp).padding(20.dp).clip(CircleShape)
            )
            Button(onClick = {navController.navigate("dish/${meal.id}")}){
                Text("See Recipe")
            }
        }
    }
}
