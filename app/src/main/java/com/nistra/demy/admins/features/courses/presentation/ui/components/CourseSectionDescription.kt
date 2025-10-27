package com.nistra.demy.admins.features.courses.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CourseSectionDescription(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            // CAMBIO: Usa primaryContainer (Azul Oscuro) para el fondo del banner
            .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(12.dp))
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Text(
            text = title,
            // CAMBIO: Usa onPrimaryContainer para el color del texto principal (Blanco/Claro)
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = description,
            // CAMBIO: Usa onPrimaryContainer para el color de la descripción
            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f),
            style = MaterialTheme.typography.bodyMedium,
            lineHeight = 18.sp
        )
    }
}
