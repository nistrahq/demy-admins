package com.nistra.demy.admins.features.classrooms.presentation.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.features.classrooms.domain.models.Classroom
import com.nistra.demy.admins.features.classrooms.presentation.model.ClassroomFormData

@Composable
fun RegisterClassroom(
    modifier: Modifier = Modifier,
    classroomToEdit: Classroom?,
    formData: ClassroomFormData,
    isLoading: Boolean,
    errorMessage: String?,
    onFormChange: (ClassroomFormData) -> Unit,
    onSaveClassroomClick: () -> Unit,
    onClearFormClick: () -> Unit
) {
    val isEditing = classroomToEdit != null
    // La validación se realiza sobre el modelo de datos inyectado
    val isFormValid = formData.code.isNotBlank() && formData.campus.isNotBlank() && formData.capacity > 0

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
                    text = if (!isEditing) "Registrar Nueva Aula" else "Editar Aula: ${classroomToEdit!!.code}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Campos de entrada
            OutlinedTextField(
                value = formData.code,
                onValueChange = { onFormChange(formData.copy(code = it)) },
                label = { Text("Código de Aula") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )

            OutlinedTextField(
                value = formData.capacityText,
                // Filtramos y luego actualizamos el modelo
                onValueChange = { onFormChange(formData.copy(capacityText = it.filter { char -> char.isDigit() })) },
                label = { Text("Capacidad") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(8.dp)
            )

            OutlinedTextField(
                value = formData.campus,
                onValueChange = { onFormChange(formData.copy(campus = it)) },
                label = { Text("Sede (Campus)") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
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
                    onClick = onSaveClassroomClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    enabled = isFormValid && !isLoading
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(20.dp))
                    } else {
                        Text(
                            text = if (!isEditing) "Registrar Aula" else "Guardar Cambios",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                // Botón de Cancelar/Limpiar formulario
                if (isEditing || formData.code.isNotBlank() || formData.capacityText.isNotBlank() || formData.campus.isNotBlank()) {
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
