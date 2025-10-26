package com.nistra.demy.admins.features.teachers.presentation.state

import com.nistra.demy.admins.features.teachers.domain.model.Teacher

data class RegisterTeacherUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null,
    val showValidationError: Boolean = false,
    val teachers: List<Teacher> = emptyList(),
    val filteredTeachers: List<Teacher> = emptyList(),
    val isLoadingTeachers: Boolean = false
)

