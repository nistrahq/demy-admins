package com.nistra.demy.admins.features.classrooms.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nistra.demy.admins.features.classrooms.presentation.ui.components.ClassroomList
import com.nistra.demy.admins.features.classrooms.presentation.ui.components.RegisterClassroom
import com.nistra.demy.admins.features.classrooms.presentation.viewmodel.ClassroomsViewModel

@Composable
fun ClassroomsScreen(
    viewModel: ClassroomsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val formData by viewModel.formData.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        RegisterClassroom(
            modifier = Modifier.weight(0.5f),
            classroomToEdit = uiState.classroomToEdit,
            formData = formData,
            isLoading = uiState.isLoading,
            errorMessage = uiState.errorMessage,
            onFormChange = viewModel::onClassroomFormChange,
            onSaveClassroomClick = viewModel::onSaveClassroomClick,
            onClearFormClick = viewModel::onClearFormClick
        )

        ClassroomList(
            modifier = Modifier.weight(0.5f),
            classrooms = uiState.classrooms,
            onClassroomSelected = viewModel::onClassroomSelectedForEdit,
            onDeleteClassroom = viewModel::onDeleteClassroomClick,
            onSearchQueryChange = viewModel::searchClassrooms,
            searchQuery = uiState.searchQuery
        )
    }
}

