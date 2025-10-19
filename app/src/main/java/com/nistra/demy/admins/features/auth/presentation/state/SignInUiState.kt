package com.nistra.demy.admins.features.auth.presentation.state

import com.nistra.demy.admins.core.ui.state.BaseUiState

data class SignInUiState(
    val username: String = "",
    val password: String = "",
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null
) : BaseUiState
