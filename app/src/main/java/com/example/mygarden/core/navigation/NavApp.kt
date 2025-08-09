package com.example.mygarden.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.mygarden.garden_configurator.presentation.GardenConfiguratorScreen
import com.example.mygarden.garden_details.presentation.screen.GardenDetailsScreen
import com.example.mygarden.gardens.presentation.screen.GardensScreen
import com.example.mygarden.search.presentation.SearchScreen
import com.example.mygarden.settings.presentation.SettingsScreen

@Composable
fun NavApp() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    Scaffold(
        floatingActionButton = {
            if(currentBackStackEntry.routeClass() == GardensGraph.GardensRoute::class) {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(GardensGraph.GardensConfiguratorRoute)
                    }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
        },
        bottomBar = {
            AppNavigationBar(navController = navController, tabs = MainTabs)
        }
    ) {  paddingValues ->
        CompositionLocalProvider(LocalNavController provides navController) {
            NavHost(
                navController = navController,
                startDestination = GardensGraph,
                modifier = Modifier.padding(paddingValues)
            ) {
                navigation<GardensGraph>(startDestination = GardensGraph.GardensRoute) {
                    composable<GardensGraph.GardensRoute> { GardensScreen() }
                    composable<GardensGraph.GardensConfiguratorRoute> { GardenConfiguratorScreen() }
                    composable<GardensGraph.GardenDetailsRoute> {
                        GardenDetailsScreen()
                    }
                }
                navigation<SearchGraph>(startDestination = SearchGraph.SearchRoute) {
                    composable<SearchGraph.SearchRoute> { SearchScreen() }
                }
                navigation<SettingsGraph>(startDestination = SettingsGraph.SettingsRoute) {
                    composable<SettingsGraph.SettingsRoute> { SettingsScreen() }
                }
            }
        }
    }
}