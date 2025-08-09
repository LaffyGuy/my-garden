package com.example.mygarden.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.cachedIn
import com.example.mygarden.core.exceptions.AppExceptions
import com.example.mygarden.core.exceptions.ExceptionToMessageMapper
import com.example.mygarden.search.domain.usecase.SearchPlantsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class SearchUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val searchQuery: String = ""
)


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchPlantsUseCase: SearchPlantsUseCase,
    private val exceptionToMessageMapper: ExceptionToMessageMapper
): ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val searchQuery = _uiState
        .map { it.searchQuery }
        .debounce(300)
        .distinctUntilChanged()

    val plantsFlow = searchQuery
        .flatMapLatest { searchQuery ->
            searchPlantsUseCase(searchQuery)
        }
        .cachedIn(viewModelScope)

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun onLoadStateChange(loadState: CombinedLoadStates) {
        val isLoading = loadState.refresh is LoadState.Loading

        val errorMessage = (loadState.refresh as? LoadState.Error)?.error?.let { error ->
            if (error is AppExceptions) {
                exceptionToMessageMapper.getLocalizedMessage(error)
            } else {
                "Unknown error"
            }
        }

        _uiState.update {
            it.copy(
                isLoading = isLoading,
                errorMessage = errorMessage
            )
        }
    }
}