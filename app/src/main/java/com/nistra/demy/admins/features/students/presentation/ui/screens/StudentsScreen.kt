package com.nistra.demy.admins.features.students.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.nistra.demy.admins.features.students.presentation.ui.components.RegisterStudent
import com.nistra.demy.admins.features.students.presentation.ui.components.StudentList
import com.nistra.demy.admins.features.students.presentation.viewmodel.StudentsViewModel

@Composable
fun StudentsScreen(
    viewModel: StudentsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val formData by viewModel.formData.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        RegisterStudent (
            modifier = Modifier.weight(0.50f),
            studentToEdit = uiState.studentToEdit,
            formData = formData,
            isLoading = uiState.isLoading,
            errorMessage = uiState.errorMessage,
            onFormChange = viewModel::onStudentFormChange,
            onSaveStudentClick = viewModel::onSaveStudentClick,
            onClearFormClick = viewModel::onClearFormClick
        )

        StudentList(
            modifier = Modifier.weight(0.50f),
            students = uiState.students,
            onStudentSelected = viewModel::onStudentSelectedForEdit,
            onDeleteStudent = viewModel::onDeleteStudentClick ,
            onSearchQueryChange = viewModel::searchStudents,
            searchQuery = uiState.searchQuery
        )
    }
}

