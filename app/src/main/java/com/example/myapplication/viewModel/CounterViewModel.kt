package com.example.myapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.CountRecord
import com.example.myapplication.repository.CountRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CounterViewModel(private val repository: CountRepository) : ViewModel() {
    // UI state exposed to the UI
    private val _uiState = MutableStateFlow(CounterUiState())
    val uiState: StateFlow<CounterUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllRecords().collect { records ->
                _uiState.update { it.copy(records = records) }
            }
        }
    }

    fun increment() {
        _uiState.update { currentState ->
            currentState.copy(
                count = currentState.count + 1
            )
        }
    }

    fun reset() {
        _uiState.update {
            it.copy(count = 0)
        }
    }

    fun saveRecord() {
        viewModelScope.launch {
            val currentCount = _uiState.value.count
            if (currentCount > 0) {
                repository.saveRecord(currentCount)
            }
        }
    }

    fun deleteRecordsExceptMostRecent() {
        viewModelScope.launch {
            repository.deleteRecordsExceptMostRecent()
        }
    }
}

data class CounterUiState(
    val count: Int = 0,
    val records: List<CountRecord> = emptyList()
)

class CounterViewModelFactory(private val repository: CountRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CounterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CounterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}