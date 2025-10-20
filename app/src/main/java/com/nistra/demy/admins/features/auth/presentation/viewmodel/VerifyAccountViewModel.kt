package com.nistra.demy.admins.features.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.features.auth.domain.usecase.VerifyAccountUseCase
import com.nistra.demy.admins.features.auth.presentation.state.VerifyAccountUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerifyAccountViewModel @Inject constructor(
    private val verifyAccountUseCase: VerifyAccountUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(VerifyAccountUiState())
    val uiState: StateFlow<VerifyAccountUiState> = _uiState

    fun verifyAccount(email: String, code: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            val result = verifyAccountUseCase(email, code)
            result
                .onSuccess {
                    _uiState.value = _uiState.value.copy(isSuccess = true, isLoading = false)
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(isLoading = false, errorMessage = exception.message)
                }
        }
    }
}
