package com.nistra.demy.admins.features.enrollments.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.nistra.demy.admins.features.enrollments.presentation.ui.components.EnrollmentList
import com.nistra.demy.admins.features.enrollments.presentation.ui.components.RegisterEnrollment
import com.nistra.demy.admins.features.enrollments.presentation.viewmodel.EnrollmentsViewModel

@Composable
fun EnrollmentsScreen(
    viewModel: EnrollmentsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val formData by viewModel.formData.collectAsState()


    Row (
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        RegisterEnrollment (
            modifier = Modifier.weight(0.50f),
            enrollmentToEdit = uiState.enrollmentToEdit,
            formData = formData,
            availableStudents = uiState.availableStudents,
            availablePeriods = uiState.availablePeriods,
            availableSchedules = uiState.availableSchedules,
            isLoading = uiState.isLoading,
            errorMessage = uiState.errorMessage,
            onFormChange = viewModel::onEnrollmentFormChange,
            onSaveEnrollmentClick = viewModel::onSaveEnrollmentClick,
            onClearFormClick = viewModel::onClearFormClick
        )

        EnrollmentList(
            modifier = Modifier.weight(0.50f),
            enrollments = uiState.enrollments,
            onEnrollmentSelected = viewModel::onEnrollmentSelectedForEdit,
            onDeleteEnrollment = viewModel::onDeleteEnrollmentClick,
            onSearchQueryChange = viewModel::searchEnrollments,
            searchQuery = uiState.searchQuery
        )
    }

}
