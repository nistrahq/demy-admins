package com.nistra.demy.admins.features.dashboard.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nistra.demy.admins.core.designsystem.preview.MainLayoutPreviewContainer
import com.nistra.demy.admins.core.designsystem.preview.TabletPreviewLight
import com.nistra.demy.admins.core.designsystem.theme.DemyTheme
import com.nistra.demy.admins.features.dashboard.domain.model.DashboardStats
import com.nistra.demy.admins.features.dashboard.presentation.model.DashboardUiState
import com.nistra.demy.admins.features.dashboard.presentation.ui.components.DashboardAcademicRow
import com.nistra.demy.admins.features.dashboard.presentation.ui.components.DashboardFinancialRow
import com.nistra.demy.admins.features.dashboard.presentation.ui.components.DashboardStatsRow
import com.nistra.demy.admins.features.dashboard.presentation.viewmodel.DashboardViewModel

/**
 * Main dashboard screen displaying key metrics and statistics.
 *
 * This screen follows Clean Architecture and MVVM pattern:
 * - Uses ViewModel to manage state and business logic
 * - Separates UI into reusable components
 * - Ready for integration with real data from repository
 *
 * @param modifier Modifier to be applied to the screen.
 * @param viewModel ViewModel that manages the dashboard state.
 * @author Salim Ramirez
 */
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    DashboardScreenContent(
        state = state,
        modifier = modifier
    )
}

/**
 * Dashboard screen content with state handling.
 */
@Composable
private fun DashboardScreenContent(
    state: DashboardUiState,
    modifier: Modifier = Modifier
) {
    when (state) {
        is DashboardUiState.Loading -> {
            LoadingState(modifier = modifier)
        }
        is DashboardUiState.Error -> {
            ErrorState(
                message = state.message ?: "An error occurred",
                modifier = modifier
            )
        }
        is DashboardUiState.Success -> {
            DashboardContent(
                state = state,
                modifier = modifier
            )
        }
    }
}

/**
 * Loading state UI.
 */
@Composable
private fun LoadingState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

/**
 * Error state UI.
 */
@Composable
private fun ErrorState(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error
        )
    }
}

/**
 * Main dashboard content displaying all statistics and information.
 */
@Composable
private fun DashboardContent(
    state: DashboardUiState.Success,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top stats row
        DashboardStatsRow(
            stats = state.stats,
            formatMoney = ::formatMoney
        )

        // Financial comparison row
        DashboardFinancialRow(
            incomes = state.stats.balance,
            expenses = state.stats.totalExpense,
            formatMoney = ::formatMoney
        )

        // Academic details row
        DashboardAcademicRow(
            totalStudents = state.stats.totalStudents,
            startDate = state.stats.academicPeriodStartDate,
            endDate = state.stats.academicPeriodEndDate,
            totalCourses = state.stats.totalCourses,
            totalClassrooms = state.stats.totalClassrooms,
            mostOverloadedTeacher = state.stats.mostOverloadedTeacher
        )
    }
}

/**
 * Formats a monetary amount to a string with currency symbol.
 *
 * @param amount The amount to format.
 * @return Formatted string with currency symbol.
 */
private fun formatMoney(amount: Int): String {
    return "S/ %,d".format(amount)
}

// ================================
// Preview
// ================================

@TabletPreviewLight
@Composable
private fun DashboardScreenPreview() {
    MainLayoutPreviewContainer(title = "Dashboard") {
        DemyTheme {
            DashboardScreenContent(
                state = DashboardUiState.Success(
                    stats = DashboardStats(
                        balance = 15000,
                        currentAcademicPeriod = 3,
                        schedules = 25,
                        totalExpense = 8500,
                        totalStudents = 200,
                        academicPeriodStartDate = "2025-08-01",
                        academicPeriodEndDate = "2025-12-15",
                        totalCourses = 38,
                        totalClassrooms = 22,
                        mostOverloadedTeacher = "Dr. Jane Smith"
                    )
                ),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

