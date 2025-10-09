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
import com.nistra.demy.admins.core.design.theme.DemyTheme
import com.nistra.demy.admins.core.ui.preview.TabletPreviewLight
import androidx.compose.ui.graphics.Color // Necesario para usar Color directamente

@Composable
fun CourseSectionDescription(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceContainerHigh, RoundedCornerShape(12.dp))
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = description,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
            lineHeight = 18.sp
        )
    }
}

// @TabletPreviewLight
// @Composable
// fun CourseSectionDescriptionPreview() {
//     DemyTheme {
//         CourseSectionDescription(
//             title = "Gestión de Cursos",
//             description = "Desde esta sección puedes registrar, editar y eliminar los cursos de la academia para mantener el control académico."
//         )
//     }
// }
