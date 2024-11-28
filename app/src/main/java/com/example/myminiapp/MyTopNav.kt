package com.example.myminiapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopNav(navController: NavController) {
    CenterAlignedTopAppBar(
        title = {
            Text("Meal Suggestions", fontSize=25.sp, fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Italic)
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            Image(painter = painterResource(R.drawable.logo), contentDescription = null,
                modifier = Modifier.size(55.dp).padding(end=10.dp))
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFFB73B)),
        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    )
}
