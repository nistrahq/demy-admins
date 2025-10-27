package com.nistra.demy.admins.features.profile.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCard
import com.nistra.demy.admins.core.designsystem.components.cards.TwoColumnInfoCard
import com.nistra.demy.admins.core.designsystem.components.feedback.DemySnackbarHost
import com.nistra.demy.admins.core.designsystem.components.feedback.SnackbarEffect
import com.nistra.demy.admins.core.designsystem.components.feedback.rememberDemySnackbarState
import com.nistra.demy.admins.core.designsystem.components.layout.ImageHeaderSection
import com.nistra.demy.admins.features.profile.presentation.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarState = rememberDemySnackbarState()

    SnackbarEffect(
        message = uiState.snackbarMessage,
        snackbarState = snackbarState,
        onMessageShown = viewModel::clearSnackbarMessage
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 🖼️ Header con foto y rol
            ImageHeaderSection(
                title = stringResource(R.string.profile_screen_title),
                description = stringResource(R.string.profile_screen_description),
                imageRes = R.drawable.sample_profile,
                name = uiState.fullName.ifBlank { "Cargando..." },
                subtitle = uiState.role.ifBlank { "—" }
            )

            // 💜 Estado de cuenta
            InfoCard(
                title = stringResource(R.string.profile_account_status_title),
                items = listOf(
                    R.string.profile_account_role to (uiState.role.ifBlank { "..." }),
                    R.string.profile_account_status to (uiState.status.ifBlank { "..." }),
                    R.string.profile_account_verification to (uiState.verification.ifBlank { "..." }),
                    R.string.profile_account_academies to uiState.academyCount.toString()
                )
            )

            // 💙 Datos personales
            TwoColumnInfoCard(
                title = stringResource(R.string.profile_general_info_title),
                items = listOf(
                    R.string.profile_field_name to (uiState.firstName.ifBlank { "..." }),
                    R.string.profile_field_lastname to (uiState.lastName.ifBlank { "..." }),
                    R.string.profile_field_phone to (uiState.phone.ifBlank { "..." }),
                    R.string.profile_field_email to (uiState.email.ifBlank { "..." }),
                    R.string.profile_field_dni to (uiState.dni.ifBlank { "..." })
                )
            )

            // 🧡 Información de academia
            TwoColumnInfoCard(
                title = stringResource(R.string.profile_academy_info_title),
                items = listOf(
                    R.string.profile_academy_name to (uiState.academyName.ifBlank { "..." }),
                    R.string.profile_academy_description to (uiState.academyDescription.ifBlank { "..." }),
                    R.string.profile_academy_phone to (uiState.academyPhone.ifBlank { "..." }),
                    R.string.profile_academy_email to (uiState.academyEmail.ifBlank { "..." }),
                    R.string.profile_academy_ruc to (uiState.academyRuc.ifBlank { "..." })
                )
            )
        }

        DemySnackbarHost(
            snackbarState = snackbarState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
