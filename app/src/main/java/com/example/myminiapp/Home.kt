package com.example.myminiapp

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.myminiapp.data.Category
import com.example.myminiapp.data.MealRepository
import com.example.myminiapp.ui.state.MealCategoryState

enum class Screen(val route:String) {
    HOME("home"),
    RANDOM("random")
}

@Composable
fun Home(navController: NavController, mealRepository: MealRepository) {
    val mealCategoryState = MealCategoryState(mealRepository)

    LaunchedEffect(mealCategoryState) {
        mealCategoryState.getCategories()
    }

    LazyColumn {
        items(mealCategoryState.mealCategories.size) {
            CategoryCard(navController, mealCategoryState.mealCategories[it])
        }
    }
}

@Composable
fun CategoryCard(navController: NavController, category: Category) {

    var isExpanded by remember {
        mutableStateOf(false)
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0x7E3CC034)),
        modifier= Modifier.fillMaxWidth()
        .padding(12.dp)
        .clickable{
            isExpanded = !isExpanded
    }
        .animateContentSize()
    ) {
        Column(
            modifier=Modifier.fillMaxWidth().padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(category.name, fontSize = 30.sp)
            AsyncImage (
                model = category.image, contentDescription = null
                , modifier=Modifier.size(250.dp)
            )
            if(isExpanded) {
                Text(category.description ?: "", fontSize = 15.sp)
                Button(
                    onClick = {navController.navigate("mealList/${category.name}")},
                    modifier=Modifier.padding(top=20.dp)
                    ) {
                    Text("See Dishes")
                }
            }
        }
    }
}