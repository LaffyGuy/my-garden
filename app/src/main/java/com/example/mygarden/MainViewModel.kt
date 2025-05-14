package com.example.mygarden

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    private val _splashScreen = MutableStateFlow(true)
    val splashScreen: StateFlow<Boolean> = _splashScreen


    init {
        viewModelScope.launch {
            delay(3000)
            _splashScreen.value = false
        }
    }

}