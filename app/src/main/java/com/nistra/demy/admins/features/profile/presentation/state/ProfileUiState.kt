package com.nistra.demy.admins.features.profile.presentation.state

import com.nistra.demy.admins.core.common.SnackbarMessage

//just to try
data class ProfileUiState(
    val fullName: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val phone: String = "",
    val email: String = "",
    val dni: String = "",
    val role: String = "",
    val status: String = "",
    val verification: String = "",
    val academyCount: Int = 0,
    val academyName: String = "",
    val academyDescription: String = "",
    val academyPhone: String = "",
    val academyEmail: String = "",
    val academyRuc: String = "",
    val snackbarMessage: SnackbarMessage? = null
)
