package com.example.myminiapp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

data class NavItem(val icon: ImageVector, val route:String)

@Composable
fun MyBottomNav(navController: NavController){

    val navItems = listOf(
        NavItem(Icons.Default.Home, Screen.HOME.route),
        NavItem(Icons.Default.ThumbUp, Screen.RANDOM.route)
    )

    NavigationBar(modifier= Modifier.height(60.dp),
        containerColor = Color(0xFFFFB73B)){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        Row(modifier=Modifier.fillMaxWidth().padding(top=15.dp)) {
            navItems.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route)
                    },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                )
            }
        }
    }
}