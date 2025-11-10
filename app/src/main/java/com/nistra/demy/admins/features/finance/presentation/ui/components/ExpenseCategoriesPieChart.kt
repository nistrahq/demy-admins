package com.nistra.demy.admins.features.finance.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.features.finance.presentation.model.PieChartData

/**
 * Expense categories chart component showing distribution as horizontal bars.
 *
 * @param data The pie chart data containing category amounts.
 * @param modifier Optional [Modifier] for the chart.
 * @author Salim Ramirez
 */
@Composable
fun ExpenseCategoriesPieChart(
    data: PieChartData,
    modifier: Modifier = Modifier
) {
    val categoryColors = listOf(
        Color(0xFFE91E63), // Pink
        Color(0xFF9C27B0), // Purple
        Color(0xFF673AB7), // Deep Purple
        Color(0xFF3F51B5), // Indigo
        Color(0xFF2196F3)  // Blue
    )

    if (data.categories.isEmpty()) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(300.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "No expense data available",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        return
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        data.categories.forEachIndexed { index, category ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            modifier = Modifier.size(12.dp),
                            shape = CircleShape,
                            color = categoryColors.getOrElse(index) { categoryColors.last() }
                        ) {}

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = category.category,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Text(
                        text = "${category.percentage.toInt()}%",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                LinearProgressIndicator(
                    progress = { category.percentage / 100f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = categoryColors.getOrElse(index) { categoryColors.last() },
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            }
        }
    }
}

