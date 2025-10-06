package com.nistra.demy.admins.features.dashboard.presentation.model

import com.nistra.demy.admins.features.dashboard.domain.model.DashboardStats

sealed interface DashboardUiState {
    data object Loading : DashboardUiState
    data class Success(val stats: DashboardStats) : DashboardUiState
    data class Error(val message: String) : DashboardUiState
}
