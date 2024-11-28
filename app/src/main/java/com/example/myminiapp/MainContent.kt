package com.example.myminiapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myminiapp.data.MealRepository

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
                MealList(
                    navController = navController, mealRepository = mealRepository,
                    category = categoryName
                )
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