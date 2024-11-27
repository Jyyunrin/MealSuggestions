package com.example.myminiapp

import android.os.Bundle
import android.util.Log
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil3.compose.AsyncImage
import com.example.myminiapp.data.MealRepository
import com.example.myminiapp.ui.state.MealCategoryState
import com.example.myminiapp.ui.state.MealState
import com.example.myminiapp.ui.theme.MyMiniAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mealRepository = (application as MyApp).mealRepository
        setContent {
            MainContent(mealRepository)
        }
    }


    @Composable
    fun MainContent(mealRepository: MealRepository) {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                MyBottomNav(navController)
            },
            topBar = {
                MyTopNav(navController)
            }
        ) { padding ->
            NavHost(
                navController, Screen.HOME.route,
                modifier = Modifier.padding(padding)
            ) {
                composable(Screen.HOME.route) {
                    Home(navController, mealRepository)
                }

                composable(Screen.RANDOM.route) {
                    Dish(mealRepository = mealRepository, id = null)
                }

                composable(
                    "mealList/{categoryName}",
                    arguments = listOf(navArgument("categoryName")
                    {
                        type = NavType.StringType
                    })
                ) {
                    val categoryName = it.arguments?.getString("categoryName")
                    MealList(navController = navController, mealRepository = mealRepository,
                        category = categoryName)
                }

                composable(
                    "dish/{id}",
                    arguments = listOf(navArgument("id")
                    {
                        type = NavType.StringType
                    })
                ) {
                    val id = it.arguments?.getString("id")
                    Dish(mealRepository = mealRepository, id = id)
                }
            }
        }
    }
}
