package com.example.mygarden.garden_configurator.presentation.viewmodel

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygarden.garden_configurator.domain.usecase.AddNewGardenUseCase
import com.example.mygarden.gardens.domain.model.Garden
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class GardenConfiguratorUiState(
    val name: String = "",
    val description: String = "",
    val area: Double = 0.0,
    val points: String = "",
    val imageUri: String = "",
    val isSaving: Boolean = false,
    val isSaved: Boolean = false,
    val errorMessage: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val exit: Unit? = null
)

@HiltViewModel
class GardenConfiguratorViewModel @Inject constructor(private val addNewGardenUseCase: AddNewGardenUseCase): ViewModel() {

    private val gson = Gson()

    private val _uiState = MutableStateFlow(GardenConfiguratorUiState())
    val uiState: StateFlow<GardenConfiguratorUiState> = _uiState.asStateFlow()

    private val _points = MutableStateFlow<List<Offset>>(emptyList())
    val points: StateFlow<List<Offset>> = _points.asStateFlow()

    private val _polygonClosed = MutableStateFlow(false)
    val polygonClosed: StateFlow<Boolean> = _polygonClosed.asStateFlow()

    fun onNameChange(newName: String) {
        _uiState.update { it.copy(name = newName) }
    }

    fun onDescriptionChange(newDescription: String) {
        _uiState.update { it.copy(description = newDescription) }
    }

    fun onAreaChange(newArea: Double) {
        _uiState.update { it.copy(area = newArea) }
    }

    fun onImageUriChange(newUri: String) {
        _uiState.update { it.copy(imageUri = newUri) }
    }

    fun updatePoints(newPoints: List<Offset>) {
        _points.value = newPoints
        updatePointsJson(newPoints)
    }

    private fun updatePointsJson(points: List<Offset>) {
        val json = gson.toJson(points)
        _uiState.update { it.copy(points = json) }
    }

    fun setPolygonClosed(value: Boolean) {
        _polygonClosed.value = value
    }

    fun savePlot() {
        viewModelScope.launch {
            val state = _uiState.value

            if (state.name.isBlank() || state.description.isBlank()) {
                _uiState.update { it.copy(errorMessage = "Назва та опис обов’язкові") }
                return@launch
            }

            _uiState.update { it.copy(isSaving = true, errorMessage = null) }

            val newGarden = Garden(
                id = 0L,
                name = state.name,
                description = state.description,
                area = state.area,
                pointsJson = state.points,
                imageUri = state.imageUri,
                createdAt = System.currentTimeMillis()
            )

            addNewGardenUseCase.invoke(newGarden)

            _uiState.update { it.copy(isSaving = false, isSaved = true, exit = Unit) }
        }
    }

    fun resetSavedFlag() {
        _uiState.update { it.copy(isSaved = false) }
    }

    fun reset() {
        _points.value = emptyList()
        _uiState.update {
            it.copy(
                name = "",
                description = "",
                area = 0.0,
                points = "",
                imageUri = "",
                errorMessage = null,
                isSaving = false,
                isSaved = false
            )
        }
        setPolygonClosed(false)
    }

    fun onExitHandled() {
        _uiState.update { it.copy(exit = null) }
    }

}