package com.nistra.demy.admins.features.settings.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class SettingsUiState(
    val darkTheme: Boolean = false,
    val highContrast: Boolean = false,
    val notificationsEnabled: Boolean = true,
    val marketingEnabled: Boolean = false
)

class SettingsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState

    fun toggleDarkTheme(enabled: Boolean) = update { it.copy(darkTheme = enabled) }

    fun toggleHighContrast(enabled: Boolean) = update { it.copy(highContrast = enabled) }

    fun toggleNotifications(enabled: Boolean) = update { it.copy(notificationsEnabled = enabled) }

    fun toggleMarketing(enabled: Boolean) = update { it.copy(marketingEnabled = enabled) }

    private fun update(transform: (SettingsUiState) -> SettingsUiState) {
        viewModelScope.launch { _uiState.value = transform(_uiState.value) }
    }
}
