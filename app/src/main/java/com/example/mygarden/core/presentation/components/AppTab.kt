package com.example.mygarden.core.presentation.components

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class AppTab(
    val icon: ImageVector,
    @StringRes val labelRes: Int,
    val graph: Any
)