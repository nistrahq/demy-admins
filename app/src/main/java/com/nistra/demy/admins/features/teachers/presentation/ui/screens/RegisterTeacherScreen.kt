package com.nistra.demy.admins.features.teachers.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.nistra.demy.admins.features.teachers.presentation.ui.components.SectionHeader
import com.nistra.demy.admins.features.teachers.presentation.ui.components.TeacherRegistrationForm
import com.nistra.demy.admins.features.teachers.presentation.ui.components.TeacherSearchPanel
import com.nistra.demy.admins.features.teachers.presentation.viewmodel.RegisterTeacherViewModel

@Composable
fun RegisterTeacherScreen(
    viewModel: RegisterTeacherViewModel = hiltViewModel(),
    onGoToList: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val formData by viewModel.formData.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    // Manejar mensajes de éxito y error
    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            snackbarHostState.showSnackbar("¡Profesor registrado exitosamente!")
            viewModel.clearSuccess()
        }
    }

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let { error ->
            snackbarHostState.showSnackbar(error)
            viewModel.clearError()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Header Section
        SectionHeader(
            title = "Gestión de Profesores",
            description = "Administre el registro de nuevos profesores y consulte el directorio completo de la academia."
        )

        // Two-column layout: Form (left) and Search (right)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Left column - Registration Form
            TeacherRegistrationForm(
                modifier = Modifier.weight(1f),
                formData = formData,
                onFormChange = viewModel::onFieldChange,
                onSubmit = viewModel::registerTeacher,
                isLoading = uiState.isLoading
            )

            // Right column - Search Panel
            TeacherSearchPanel(
                modifier = Modifier.weight(1f),
                searchQuery = searchQuery,
                onSearchQueryChange = viewModel::onSearchQueryChange,
                teachers = uiState.filteredTeachers,
                isLoading = uiState.isLoadingTeachers
            )
        }

        // Snackbar host for messages
        SnackbarHost(
            hostState = snackbarHostState,
            snackbar = { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = if (uiState.errorMessage != null) {
                        MaterialTheme.colorScheme.errorContainer
                    } else {
                        MaterialTheme.colorScheme.primaryContainer
                    },
                    contentColor = if (uiState.errorMessage != null) {
                        MaterialTheme.colorScheme.onErrorContainer
                    } else {
                        MaterialTheme.colorScheme.onPrimaryContainer
                    }
                )
            }
        )
    }
}

@Preview(showBackground = true, widthDp = 1200, heightDp = 800)
@Composable
fun RegisterTeacherScreenPreview() {
    MaterialTheme {
        RegisterTeacherScreen()
    }
}
