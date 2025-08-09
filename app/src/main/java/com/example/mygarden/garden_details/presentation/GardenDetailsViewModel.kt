package com.example.mygarden.garden_details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygarden.core.data.LoadResult
import com.example.mygarden.garden_details.domain.GardenDetailsRepository
import com.example.mygarden.gardens.domain.model.Garden
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class GardenDetailsUiState(
    val garden: Garden? = null,
    val isGardenDeleted: Boolean = false
)


@HiltViewModel
class GardenDetailsViewModel @Inject constructor(
    private val gardenDetailsRepository: GardenDetailsRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val gardenId: Long = savedStateHandle.get<Long>("gardenId") ?: -1L

    private val _uiState = MutableStateFlow<LoadResult<GardenDetailsUiState>>(LoadResult.Loading)
    val uiState: StateFlow<LoadResult<GardenDetailsUiState>> = _uiState.asStateFlow()


    init {
        load()
    }


    private fun load() {
        viewModelScope.launch {
            _uiState.value = LoadResult.Loading
            try {
                val garden = gardenDetailsRepository.getGardenById(gardenId)
                _uiState.value = LoadResult.Success(GardenDetailsUiState(garden))
            } catch (e: Exception) {
                _uiState.value = LoadResult.Error(e)
            }
        }
    }

}