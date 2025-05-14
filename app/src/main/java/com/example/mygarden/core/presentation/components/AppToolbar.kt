package com.example.mygarden.core.presentation.components

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mygarden.R
import com.example.mygarden.ui.theme.AppTheme

sealed class NavigateUpAction {
    data object Hidden: NavigateUpAction()
    data class Visible(
        val onClick: () -> Unit
    ): NavigateUpAction()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(
    @StringRes titleRes: Int,
    navigateUpAction: NavigateUpAction
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(id = titleRes))
        },
        navigationIcon = {
            if(navigateUpAction is NavigateUpAction.Visible) {
                IconButton(
                    onClick = navigateUpAction.onClick
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )

    )
}

@Preview(showSystemUi = true)
@Composable
fun AppToolbarPreview() {
    AppTheme {
        AppToolbar(titleRes = R.string.app_name, navigateUpAction = NavigateUpAction.Visible({}))
    }
}







