package com.nistra.demy.admins.features.profile.presentation.state

import com.nistra.demy.admins.core.common.SnackbarMessage
import com.nistra.demy.admins.features.profile.data.remote.ProfileStats

data class ProfileUiState(
    val isLoading: Boolean = false,
    val profileStats: ProfileStats? = null,
    val error: String? = null
)
