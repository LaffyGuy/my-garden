package com.example.mygarden.details.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygarden.core.data.LoadResult
import com.example.mygarden.details.data.dto.PlantDetailsResponse
import com.example.mygarden.details.domain.usecase.PlantDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantDetailsViewModel @Inject constructor(private val plantDetailsUseCase: PlantDetailsUseCase): ViewModel() {

    private val _uiState = MutableStateFlow<LoadResult<PlantDetailsUiState>>(LoadResult.Loading)
    val uiState: StateFlow<LoadResult<PlantDetailsUiState>> = _uiState


    fun getPlantDetails() {
        viewModelScope.launch {
            plantDetailsUseCase(1).collect { result ->
                when(result) {
                    is LoadResult.Loading -> {
                        _uiState.value = LoadResult.Loading
                    }
                    is LoadResult.Success -> {
                        val uiState = PlantDetailsUiState(data = result.data)
                        _uiState.value = LoadResult.Success(uiState)
                    }
                    is LoadResult.Error -> {
                        _uiState.value = LoadResult.Error(result.exception)
                    }
                }
            }

        }
    }

}

data class PlantDetailsUiState(val data: PlantDetailsResponse? = null)