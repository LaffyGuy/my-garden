package com.example.mygarden.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mygarden.gardens.presentation.screen.GardensScreen

@Composable
fun NavApp() {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(navController = navController, startDestination = GardensRoute) {
            composable<GardensRoute> { GardensScreen() }
        }
    }

}