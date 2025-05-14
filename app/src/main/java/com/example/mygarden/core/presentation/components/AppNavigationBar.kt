package com.example.mygarden.core.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.mygarden.ui.theme.AppTheme
import kotlinx.collections.immutable.ImmutableList

@Composable
fun AppNavigationBar(
//    navController: NavController,
//    tabs: ImmutableList<AppTab>
) {

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.secondaryContainer
    ) {
    }

}

@Preview(showSystemUi = true)
@Composable
fun AppNavigationBarPreview() {
    AppTheme {
        AppNavigationBar()
    }
}