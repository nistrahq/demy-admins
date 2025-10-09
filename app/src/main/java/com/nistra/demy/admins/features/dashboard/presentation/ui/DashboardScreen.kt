package com.nistra.demy.admins.features.dashboard.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
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
import com.nistra.demy.admins.core.design.theme.DemyTheme
import com.nistra.demy.admins.core.design.theme.extendedColors
import com.nistra.demy.admins.core.ui.preview.MainLayoutPreviewContainer
import com.nistra.demy.admins.core.ui.preview.TabletPreviewLight
import com.nistra.demy.admins.features.dashboard.presentation.model.DashboardUiState
import com.nistra.demy.admins.core.ui.components.SmallCard
import com.nistra.demy.admins.features.dashboard.presentation.ui.components.AcademicPeriodDetailsCard
import com.nistra.demy.admins.features.dashboard.presentation.ui.components.ClassroomsAndCoursesCard
import com.nistra.demy.admins.features.dashboard.presentation.ui.components.InfoCardTwoColumn
import com.nistra.demy.admins.features.dashboard.presentation.ui.components.MostOverloadedTeacherCard
import com.nistra.demy.admins.features.dashboard.presentation.ui.components.TopExpenseCategoriesCard
import com.nistra.demy.admins.features.dashboard.presentation.viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    when (val s = state) {
        is DashboardUiState.Loading -> Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) { CircularProgressIndicator() }

        is DashboardUiState.Error -> Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) { Text(text = "Error") }

        is DashboardUiState.Success -> DashboardContent(
            modifier = modifier.fillMaxSize(),
            balance = s.stats.balance,
            currentAcademicPeriod = s.stats.currentAcademicPeriod,
            schedules = s.stats.schedules,
            totalExpense = s.stats.totalExpense
        )
    }
}

@Composable
private fun DashboardContent(
    modifier: Modifier = Modifier,
    balance: Int,
    currentAcademicPeriod: Int,
    schedules: Int,
    totalExpense: Int
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SmallCard(
                label = "Balance",
                value = formatMoney(balance),
                modifier = Modifier.weight(1f),
                containerColor = MaterialTheme.extendedColors.success.colorContainer.copy(alpha = 0.2f)
            )
            SmallCard(
                label = "Current Academic Period",
                value = currentAcademicPeriod.toString(),
                modifier = Modifier.weight(1f),
                containerColor = MaterialTheme.extendedColors.success.colorContainer.copy(alpha = 0.2f)
            )
            SmallCard(
                label = "Schedules",
                value = schedules.toString(),
                modifier = Modifier.weight(1f),
                containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f)
            )
            SmallCard(
                label = "Total Expense",
                value = formatMoney(totalExpense),
                modifier = Modifier.weight(1f),
                containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            InfoCardTwoColumn(
                title = "Incomes vs Expenses",
                leftLabel = "Incomes",
                leftValue = formatMoney(balance),
                rightLabel = "Expenses",
                rightValue = formatMoney(totalExpense),
                modifier = Modifier
                    .weight(1f)
                    .heightIn(min = 220.dp)
            )
            TopExpenseCategoriesCard(
                modifier = Modifier
                    .weight(1f)
                    .heightIn(min = 220.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AcademicPeriodDetailsCard(
                totalStudents = 200,          // TODO: replace with real data
                startDate = "2025-08-01",     // TODO
                endDate = "2025-12-15",       // TODO
                modifier = Modifier.weight(1f)
            )
            MostOverloadedTeacherCard(
                teacherName = "Dr. Jane Smith", // TODO: replace with real data
                modifier = Modifier.weight(1f)
            )
            ClassroomsAndCoursesCard(
                totalCourses = 38,           // TODO
                totalClassrooms = 22,        // TODO
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@TabletPreviewLight
@Composable
fun DashboardContentPreview() {
    MainLayoutPreviewContainer(title = "Dashboard") {
        DemyTheme {
            DashboardContent(
                modifier = Modifier.fillMaxSize(),
                balance = 1500,
                currentAcademicPeriod = 3,
                schedules = 25,
                totalExpense = 5000
            )
        }
    }
}

private fun formatMoney(amount: Int): String {
    return "S/ " + "%,d".format(amount)
}
