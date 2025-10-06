package com.nistra.demy.admins.features.dashboard.presentation

data class DashboardUiState(
    val isLoading: Boolean = false,
    val totalTeachers: Int = 0,
    val totalStudents: Int = 0,
    val activeCourses: Int = 0
)
