package com.nistra.demy.admins.core.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * A small card component displaying a label and a value.
 *
 * Designed for use in multiple card layouts.
 * Provides customization options for colors and dimensions.
 * Adapts to light and dark themes.
 *
 * @param label The label text to display.
 * @param value The value text to display.
 * @param modifier Optional [Modifier] for the card.
 * @param containerColor Background color of the card.
 * @param valueColor Color of the value text.
 * @param labelColor Color of the label text.
 * @param minWidthDp Minimum width of the card in dp.
 * @param minHeightDp Minimum height of the card in dp.
 * @author Salim Ramirez
 */
@Composable
fun SmallCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    borderColor: Color = MaterialTheme.colorScheme.outlineVariant,
    valueColor: Color = MaterialTheme.colorScheme.onSurface,
    labelColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    minWidthDp: Int = 120,
    minHeightDp: Int = 80,
) {
    OutlinedCard(
        modifier = modifier
            .sizeIn(minWidth = minWidthDp.dp, minHeight = minHeightDp.dp)
            .padding(4.dp),
        colors = CardDefaults.outlinedCardColors(containerColor = containerColor),
        border = BorderStroke(1.dp, borderColor),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall.copy(color = labelColor)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 28.sp,
                    color = valueColor
                )
            )
        }
    }
}
