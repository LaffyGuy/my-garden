package com.example.mygarden.core.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mygarden.R
import kotlinx.collections.immutable.persistentListOf

data class AppTab(
    val icon: ImageVector,
    @StringRes val label: Int,
    val graph: Any,
)

val MainTabs = persistentListOf(
    AppTab(
        icon = Icons.Default.List,
        label = R.string.gardens,
        graph = GardensGraph
    ),
    AppTab(
        icon = Icons.Default.Search,
        label = R.string.search,
        graph = SearchGraph
    ),
    AppTab(
        icon = Icons.Default.List,
        label = R.string.settings,
        graph = SettingsGraph
    )

)