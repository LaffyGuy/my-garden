package com.example.mygarden

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.mygarden.core.logger.Logger
import com.example.mygarden.core.navigation.AppNavigationBar
import com.example.mygarden.core.navigation.GardensGraph
import com.example.mygarden.core.navigation.KeyFeatureRoute
import com.example.mygarden.core.navigation.LocalNavController
import com.example.mygarden.core.navigation.MainTabs
import com.example.mygarden.core.navigation.SearchGraph
import com.example.mygarden.core.navigation.SettingsGraph
import com.example.mygarden.core.navigation.SplashRoute
import com.example.mygarden.core.navigation.routeClass
import com.example.mygarden.core.presentation.components.AppToolbar
import com.example.mygarden.core.presentation.components.NavigateUpAction
import com.example.mygarden.garden_configurator.presentation.GardenConfiguratorScreen
import com.example.mygarden.garden_details.presentation.screen.GardenDetailsScreen
import com.example.mygarden.gardens.presentation.screen.GardensScreen
import com.example.mygarden.key_feature.presentation.screens.KeyFeatureScreen
import com.example.mygarden.search.presentation.SearchScreen
import com.example.mygarden.settings.presentation.SettingsScreen
import com.example.mygarden.splash.SplashScreen
import com.example.mygarden.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var logger: Logger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                NavApp()
            }
        }
    }
}

@Composable
fun NavApp() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val titleRes = when(currentBackStackEntry.routeClass()) {
        GardensGraph.GardensRoute::class -> R.string.gardens
        SearchGraph.SearchRoute::class -> R.string.search
        SettingsGraph.SettingsRoute::class -> R.string.settings
        else -> R.string.app_name

    }
    Scaffold(
        topBar = {
            AppToolbar(
                titleRes = titleRes,
                navigateUpAction = if(navController.previousBackStackEntry == null) {
                    NavigateUpAction.Hidden
                } else {
                    NavigateUpAction.Visible(
                        onClick = {
                            navController.navigateUp()
                        }
                    )
                }
            )
        },
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
            if(currentBackStackEntry.routeClass() != KeyFeatureRoute::class && currentBackStackEntry.routeClass() != SplashRoute::class) {
                AppNavigationBar(navController = navController, tabs = MainTabs)
            }
        }
    ) {  paddingValues ->
        CompositionLocalProvider(LocalNavController provides navController) {
            NavHost(
                navController = navController,
                startDestination = SplashRoute,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable<SplashRoute> { SplashScreen() }
                composable<KeyFeatureRoute> { KeyFeatureScreen() }
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


@Preview
@Composable
fun MainScreen() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
        )
    }
}

