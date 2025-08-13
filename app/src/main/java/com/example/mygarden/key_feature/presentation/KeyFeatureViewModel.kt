package com.example.mygarden.key_feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygarden.key_feature.domain.model.KeyFeature
import com.example.mygarden.key_feature.domain.usecase.GetKeyFeatureUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KeyFeatureViewModel @Inject constructor(private val getKeyFeatureUseCase: GetKeyFeatureUseCase): ViewModel() {

    private val _keyFeature = MutableStateFlow<List<KeyFeature>>(emptyList())
    val keyFeature = _keyFeature.asStateFlow()


    init {
        initKeyFeature()
    }

    private fun initKeyFeature() {
        viewModelScope.launch {
            try {
                val features = getKeyFeatureUseCase()
                _keyFeature.value = features
            } catch (e: Exception) {
                _keyFeature.value = emptyList()
            }
        }
    }



}