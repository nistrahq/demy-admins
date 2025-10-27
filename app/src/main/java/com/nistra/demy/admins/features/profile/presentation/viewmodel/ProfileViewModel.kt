package com.nistra.demy.admins.features.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.nistra.demy.admins.features.profile.presentation.state.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun clearSnackbarMessage() { /*back */ }
}
