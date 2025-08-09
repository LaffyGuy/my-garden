package com.example.mygarden

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mygarden.core.logger.Logger
import com.example.mygarden.core.navigation.NavApp
import com.example.mygarden.details.presentation.viewmodel.PlantDetailsViewModel
import com.example.mygarden.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var logger: Logger

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Logger.d("AAA Hello djajdajda")
        logger.d("AAA Hello DI")

        enableEdgeToEdge()
        installSplashScreen().apply {
            mainViewModel.splashScreen.value
        }
        setContent {
            AppTheme {
                NavApp()
            }
        }
    }
}

@Composable
fun Initial() {
    val viewModel: PlantDetailsViewModel = hiltViewModel()

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getPlantDetails()
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

