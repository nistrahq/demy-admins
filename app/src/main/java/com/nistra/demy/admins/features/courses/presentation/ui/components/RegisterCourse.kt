package com.nistra.demy.admins.features.courses.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.features.courses.domain.models.Course // Tu modelo de dominio
import com.nistra.demy.admins.features.courses.presentation.model.CourseFormData

@Composable
fun RegisterCourse(
    modifier: Modifier = Modifier,
    courseToEdit: Course?,
    formData: CourseFormData,
    isLoading: Boolean,
    errorMessage: String?,
    onFormChange: (CourseFormData) -> Unit,
    onSaveCourseClick: () -> Unit,
    onClearFormClick: () -> Unit
) {
    val isEditing = courseToEdit != null

    Card(
        modifier = modifier.fillMaxHeight(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Título del formulario
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = if (!isEditing) "Registrar Nuevo Curso" else "Editar Curso: ${courseToEdit.code}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Campos de entrada - Usa onFormChange para actualizar el estado
            OutlinedTextField(
                value = formData.name,
                onValueChange = { onFormChange(formData.copy(name = it)) },
                label = { Text("Nombre del Curso") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )

            OutlinedTextField(
                value = formData.code,
                onValueChange = { onFormChange(formData.copy(code = it)) },
                label = { Text("Código de Curso") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )

            OutlinedTextField(
                value = formData.description,
                onValueChange = { onFormChange(formData.copy(description = it)) },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 4,
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Mensaje de error
            if (errorMessage != null) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Botones de Acción
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Botón de Guardar/Registrar
                Button(
                    onClick = onSaveCourseClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    enabled = formData.name.isNotBlank() && formData.code.isNotBlank() && formData.description.isNotBlank() && !isLoading,
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(20.dp))
                    } else {
                        Text(
                            text = if (!isEditing) "Registrar Curso" else "Guardar Cambios",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                // Botón de Cancelar/Limpiar formulario (condicional)
                if (isEditing || formData.name.isNotBlank() || formData.code.isNotBlank() || formData.description.isNotBlank()) {
                    OutlinedButton(
                        onClick = onClearFormClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        enabled = !isLoading
                    ) {
                        Icon(Icons.Default.Clear, contentDescription = "Cancelar")
                        Spacer(Modifier.width(4.dp))
                        Text("Cancelar")
                    }
                }
            }
        }
    }
}
