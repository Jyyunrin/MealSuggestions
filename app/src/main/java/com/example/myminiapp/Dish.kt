package com.example.myminiapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import coil3.compose.AsyncImage
import com.example.myminiapp.data.Dish
import com.example.myminiapp.data.MealRepository
import com.example.myminiapp.ui.state.MealState

@Composable
fun Dish(mealRepository: MealRepository, id: String?) {
    val mealState = remember { MealState(mealRepository) }

    LaunchedEffect(id) {
        if (!id.isNullOrEmpty()) {
            mealState.getDish(id)
        } else {
            mealState.getRandomDish()
        }
    }


    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFFFF0A8))) {
        LazyColumn(modifier = Modifier.padding(20.dp)) {
            items(mealState.mealsIndividual.size) {
                DishPage(mealState.mealsIndividual[it])
            }
        }
    }
}

@Composable
fun DishPage(dish: Dish) {
    Column(modifier=Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(dish.name, fontSize = 40.sp, fontWeight= FontWeight.Bold,
            textAlign = TextAlign.Center)
        Spacer(modifier=Modifier.padding(top=20.dp))
        AsyncImage(
            model = dish.image,
            contentDescription = null,
            modifier = Modifier.size(250.dp).clip(RoundedCornerShape(20.dp))
        )
        Text("Origin: ${dish.location}", fontSize = 20.sp)
    }

    Column(modifier = Modifier.padding(top=20.dp)) {
        Text("Ingredients:", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        for(ingredient in dish.ingredients) { Text(ingredient, fontSize = 20.sp)
            }
        Spacer(modifier=Modifier.padding(top=20.dp))
        Text("Instructions:", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Text(dish.instructions, fontSize = 20.sp)
    }
}