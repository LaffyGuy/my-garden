package com.example.mygarden.gardens.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygarden.core.data.LoadResult
import com.example.mygarden.gardens.domain.model.Garden
import com.example.mygarden.gardens.domain.usecase.GardensUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GardensViewModel @Inject constructor(private val gardensUseCase: GardensUseCase): ViewModel() {

    private val _gardensState = MutableStateFlow<LoadResult<List<Garden>>>(LoadResult.Loading)
    val gardensState: StateFlow<LoadResult<List<Garden>>> = _gardensState.asStateFlow()

    private val _visiblePermissionDialog = MutableStateFlow<List<String>>(emptyList())
    val visiblePermissionDialog = _visiblePermissionDialog.asStateFlow()


    init {
        loadGardens()
    }

    private fun loadGardens() {
        viewModelScope.launch {
            gardensUseCase()
                .collect { result ->
                    _gardensState.value = result
                }
        }
    }

    fun refreshGardens() {
        loadGardens()
    }


    fun dismissDialog() {
        _visiblePermissionDialog.update { it.drop(1) }
    }

    fun onPermissionResult(permission: String, isGranted: Boolean) {
        if(!isGranted && !_visiblePermissionDialog.value.contains(permission)) {
            _visiblePermissionDialog.update { it + permission }
        }
    }

}