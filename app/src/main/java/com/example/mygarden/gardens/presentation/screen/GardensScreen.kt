package com.example.mygarden.gardens.presentation.screen

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mygarden.R
import com.example.mygarden.core.data.LoadResult
import com.example.mygarden.core.data.data.GalleryPermissionTextProvider
import com.example.mygarden.core.navigation.GardensGraph
import com.example.mygarden.core.navigation.LocalNavController
import com.example.mygarden.core.presentation.components.PermissionDialog
import com.example.mygarden.gardens.domain.model.Garden
import com.example.mygarden.gardens.presentation.GardensViewModel
import com.example.mygarden.gardens.presentation.components.GardenItem


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun GardensScreen() {
    val viewModel: GardensViewModel = hiltViewModel()
    val state by viewModel.gardensState.collectAsStateWithLifecycle()
    val dialogQueue by viewModel.visiblePermissionDialog.collectAsStateWithLifecycle()
    val navController = LocalNavController.current

    val context = LocalContext.current
    val activity = context as? Activity

    val permission = Manifest.permission.READ_MEDIA_IMAGES

    val permissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            viewModel.onPermissionResult(
                permission = permission,
                isGranted = isGranted
            )
        }
    )

    LaunchedEffect(Unit) {
        permissionResultLauncher.launch(permission)
    }

    GardensContent(
        loadResult = state,
        tryAgainAction = { viewModel.refreshGardens() },
        navigateToGardenDetails = { gardenId ->
            navController.navigate(GardensGraph.GardenDetailsRoute(gardenId))
        }
    )

    dialogQueue.reversed().forEach { perm ->
        PermissionDialog(
            permissionTextProvider = GalleryPermissionTextProvider(),
            isPermanentlyDeclined = activity?.let {
                !ActivityCompat.shouldShowRequestPermissionRationale(it, perm)
            } ?: false,
            onDismiss = { viewModel.dismissDialog() },
            onOkClick = {
                viewModel.dismissDialog()
                permissionResultLauncher.launch(perm)
            },
            onGoToAppSettingsClick = {
                context.openAppSettings()
                viewModel.dismissDialog()
            }
        )
    }
}

@Composable
fun GardensContent(
    loadResult: LoadResult<List<Garden>>,
    tryAgainAction: () -> Unit,
    navigateToGardenDetails: (gardenId: Long) -> Unit
) {
    when(loadResult) {
        is LoadResult.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is LoadResult.Success -> {
            if(loadResult.data.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_empty_list),
                        contentDescription = null,
                        modifier = Modifier.size(200.dp)
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(loadResult.data) { garden ->
                        GardenItem(
                            name = garden.name,
                            description = garden.description,
                            imageUri = garden.imageUri,
                            modifier = Modifier.clickable {
                                navigateToGardenDetails(garden.id)
                            }
                        )
                    }
                }
            }
        }
        is LoadResult.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Something went wrong, please try again.")
                Button(
                    onClick = tryAgainAction
                ) {
                    Text("Try Again")
                }
            }
        }
    }
}

fun Context.openAppSettings() {
    val intent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    )
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
}

@Preview(showSystemUi = true)
@Composable
fun GardensPreview() {
    GardensContent(
        loadResult = LoadResult.Loading,
        tryAgainAction = {},
        navigateToGardenDetails = {}
    )
}
