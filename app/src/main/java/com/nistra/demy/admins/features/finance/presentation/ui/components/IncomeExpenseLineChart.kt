package com.nistra.demy.admins.features.finance.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import com.nistra.demy.admins.features.finance.presentation.model.LineChartData

/**
 * Line chart component showing income and expense trends over time.
 *
 * @param data The line chart data containing income and expense points.
 * @param modifier Optional [Modifier] for the chart.
 * @author Salim Ramirez
 */
@Composable
fun IncomeExpenseLineChart(
    data: LineChartData,
    modifier: Modifier = Modifier
) {
    val incomeColor = Color(0xFF4CAF50) // Green
    val expenseColor = Color(0xFFF44336) // Red

    val modelProducer = remember { CartesianChartModelProducer() }

    // Extract dates for display
    val dateLabels = remember(data) {
        if (data.incomeData.isNotEmpty()) {
            data.incomeData.map {
                val parts = it.date.split("-")
                if (parts.size == 3) "${parts[1]}/${parts[2]}" else it.date
            }
        } else if (data.expenseData.isNotEmpty()) {
            data.expenseData.map {
                val parts = it.date.split("-")
                if (parts.size == 3) "${parts[1]}/${parts[2]}" else it.date
            }
        } else {
            emptyList()
        }
    }

    // Update chart data
    LaunchedEffect(data) {
        modelProducer.runTransaction {
            lineSeries {
                if (data.incomeData.isNotEmpty()) {
                    series(data.incomeData.map { it.amount })
                }
                if (data.expenseData.isNotEmpty()) {
                    series(data.expenseData.map { it.amount })
                }
            }
        }
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        CartesianChartHost(
            chart = rememberCartesianChart(
                rememberLineCartesianLayer()
            ),
            modelProducer = modelProducer,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Date labels row
        if (dateLabels.isNotEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                dateLabels.take(5).forEach { date ->
                    Text(
                        text = date,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }

        // Legend
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(12.dp),
                    shape = CircleShape,
                    color = incomeColor
                ) {}

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "Income",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(12.dp),
                    shape = CircleShape,
                    color = expenseColor
                ) {}

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "Expense",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

