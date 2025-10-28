package com.nistra.demy.admins.features.profile.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.features.profile.presentation.state.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadProfile()
    }

    fun loadProfile() = viewModelScope.launch {
        _uiState.value = ProfileUiState.Loading
        runCatching { repository.fetchProfileStats() }
            .onSuccess { _uiState.value = ProfileUiState.Success(it) }
            .onFailure { _uiState.value = ProfileUiState.Error("Failed to load profile") }
    }
}
