package com.nistra.demy.admins.features.dashboard.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.core.designsystem.components.SmallCard
import com.nistra.demy.admins.core.designsystem.theme.extendedColors
import com.nistra.demy.admins.features.dashboard.domain.model.DashboardStats

/**
 * Top row displaying key dashboard statistics.
 *
 * Shows balance, current academic period, schedules, and total expense
 * in a horizontal row of small cards with consistent height.
 *
 * @param stats Dashboard statistics to display.
 * @param formatMoney Function to format monetary values.
 * @param modifier Modifier to be applied to the row.
 * @author Salim Ramirez
 */
@Composable
fun DashboardStatsRow(
    stats: DashboardStats,
    formatMoney: (Int) -> String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SmallCard(
            label = "Balance",
            value = formatMoney(stats.balance),
            modifier = Modifier.weight(1f),
            containerColor = MaterialTheme.extendedColors.success.colorContainer.copy(alpha = 0.3f)
        )
        SmallCard(
            label = "Current Academic Period",
            value = stats.currentAcademicPeriod.toString(),
            modifier = Modifier.weight(1f),
            containerColor = MaterialTheme.extendedColors.info.colorContainer.copy(alpha = 0.3f)
        )
        SmallCard(
            label = "Schedules",
            value = stats.schedules.toString(),
            modifier = Modifier.weight(1f),
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)
        )
        SmallCard(
            label = "Total Expense",
            value = formatMoney(stats.totalExpense),
            modifier = Modifier.weight(1f),
            containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.5f)
        )
    }
}

