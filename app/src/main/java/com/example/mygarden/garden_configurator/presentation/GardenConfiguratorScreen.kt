package com.example.mygarden.garden_configurator.presentation

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.mygarden.core.navigation.LocalNavController
import com.example.mygarden.garden_configurator.presentation.viewmodel.GardenConfiguratorViewModel
import kotlin.math.hypot
import kotlin.math.roundToInt

@Composable
fun GardenConfiguratorScreen() {

    val navController = LocalNavController.current

    val viewModel: GardenConfiguratorViewModel = hiltViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle(
        minActiveState = Lifecycle.State.RESUMED
    )
    val points by viewModel.points.collectAsStateWithLifecycle()
    val polygonClosed by viewModel.polygonClosed.collectAsStateWithLifecycle()

    val context = LocalContext.current


    LaunchedEffect(uiState) {
        if (uiState.isSaved) {
            Toast.makeText(context, "Ділянку збережено!", Toast.LENGTH_SHORT).show()
            viewModel.resetSavedFlag()
            uiState.exit?.let {
                navController.popBackStack()
                viewModel.onExitHandled()
            }
        }
    }

    GardenConfiguratorContent(
        points = points,
        polygonClosed = polygonClosed,
        name = uiState.name,
        description = uiState.description,
        imageUri = uiState.imageUri,
        area = uiState.area,
        onUpdatePoints = viewModel::updatePoints,
        onSetPolygonClosed = viewModel::setPolygonClosed,
        onNameChange = viewModel::onNameChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onImageUriChange = viewModel::onImageUriChange,
        onAreaChange = viewModel::onAreaChange,
        onSaveClick = viewModel::savePlot,
        onReset = viewModel::reset,
        isSaving = uiState.isSaving,
        errorMessage = uiState.errorMessage
    )
}

@Composable
fun GardenConfiguratorContent(
    points: List<Offset>,
    polygonClosed: Boolean,
    name: String,
    description: String,
    imageUri: String,
    area: Double,
    onUpdatePoints: (List<Offset>) -> Unit,
    onSetPolygonClosed: (Boolean) -> Unit,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onImageUriChange: (String) -> Unit,
    onAreaChange: (Double) -> Unit,
    onSaveClick: () -> Unit,
    onReset: () -> Unit,
    isSaving: Boolean,
    errorMessage: String?
) {

    val gridSizePx = 100f
    val closeThreshold = 30f

    var localImageUri by remember(imageUri) {
        mutableStateOf(imageUri.takeIf { it.isNotBlank() }?.let { Uri.parse(it) })
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .pointerInput(points, polygonClosed) {
                    detectTapGestures { offset ->
                        if (polygonClosed) return@detectTapGestures

                        val snapped = Offset(
                            (offset.x / gridSizePx).roundToInt() * gridSizePx,
                            (offset.y / gridSizePx).roundToInt() * gridSizePx
                        )

                        if (points.isNotEmpty()) {
                            val dist = hypot(
                                (snapped.x - points.first().x),
                                (snapped.y - points.first().y)
                            )
                            if (dist < closeThreshold && points.size >= 3) {
                                onSetPolygonClosed(true)
                                val newArea = calculatePolygonArea(points) * 0.0001
                                onAreaChange(newArea)
                                return@detectTapGestures
                            }
                        }
                        onUpdatePoints(points + snapped)
                    }
                }
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                for (x in 0..size.width.toInt() step gridSizePx.toInt()) {
                    drawLine(Color.LightGray, Offset(x.toFloat(), 0f), Offset(x.toFloat(), size.height))
                }
                for (y in 0..size.height.toInt() step gridSizePx.toInt()) {
                    drawLine(Color.LightGray, Offset(0f, y.toFloat()), Offset(size.width, y.toFloat()))
                }

                points.forEach { drawCircle(Color.Red, 8f, it) }

                if (points.size > 1) {
                    val path = Path().apply {
                        moveTo(points.first().x, points.first().y)
                        points.drop(1).forEach { lineTo(it.x, it.y) }
                        if (polygonClosed) close()
                    }
                    drawPath(path, Color.Blue, style = Stroke(width = 4f))
                }
            }
        }

        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = if (polygonClosed) "Площа: %.2f м²".format(area) else "Тапни першу точку, щоб завершити",
                style = MaterialTheme.typography.bodyLarge
            )

            if (polygonClosed) {
                OutlinedTextField(
                    value = name,
                    onValueChange = onNameChange,
                    label = { Text("Назва ділянки") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(8.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = onDescriptionChange,
                    label = { Text("Опис") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(8.dp))

                val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                    localImageUri = uri
                    onImageUriChange(uri?.toString() ?: "")
                }

                Button(onClick = { launcher.launch("image/*") }) {
                    Text("Вибрати фото")
                }

                localImageUri?.let {
                    Spacer(Modifier.height(8.dp))
                    AsyncImage(
                        model = it,
                        contentDescription = "Фото ділянки",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }

                Spacer(Modifier.height(8.dp))

                Button(
                    onClick = onSaveClick,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isSaving
                ) {
                    if (isSaving) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(24.dp)
                                .padding(end = 8.dp),
                            strokeWidth = 2.dp
                        )
                    }
                    Text("Зберегти")
                }
            }

            Spacer(Modifier.height(8.dp))

            Button(onClick = {
                onReset()
            }) {
                Text("Скинути")
            }

            errorMessage?.let {
                Spacer(Modifier.height(8.dp))
                Text(it, color = Color.Red)
            }
        }
    }

}




@Preview(showSystemUi = true)
@Composable
fun GardenConfiguratorContentPreview() {
//    GardenConfiguratorContent({})
}