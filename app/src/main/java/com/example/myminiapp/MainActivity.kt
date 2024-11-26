package com.example.myminiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.myminiapp.ui.state.MealCategoryState
import com.example.myminiapp.ui.state.MealState
import com.example.myminiapp.ui.theme.MyMiniAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mealRepository = (application as MyApp).mealRepository
        setContent {
            val mealState = MealState(mealRepository)
            val mealCategoryState = MealCategoryState(mealRepository)

            LaunchedEffect(mealCategoryState) {
                mealCategoryState.getCategories()
            }

            LazyColumn {
                items(mealCategoryState.mealCategories.size) {
                    Text(mealCategoryState.mealCategories[it].name, fontSize = 30.sp)
                    AsyncImage (
                        model = mealCategoryState.mealCategories[it].image, contentDescription = null
                    )
                }
            }
        }
    }
}