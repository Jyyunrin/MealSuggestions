package com.example.myminiapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopNav(navController: NavController) {
    MediumTopAppBar(
        title = {
            Text("Meal Suggestions")
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate(Screen.HOME.route)
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
        }
    )
}
