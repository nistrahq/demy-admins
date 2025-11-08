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

@Composable
fun RegisterClassroom(
    modifier: Modifier = Modifier,
    classroomToEdit: Classroom?,
    onSaveClassroom: (Classroom) -> Unit,
    onClearForm: () -> Unit
) {
    // 1. Manejo del Estado Local del Formulario
    var code by remember { mutableStateOf("") }
    var capacityText by remember { mutableStateOf("") }
    var campus by remember { mutableStateOf("") }

    // Validación y conversión de capacidad
    val capacity = capacityText.toIntOrNull() ?: 0
    val isFormValid = code.isNotBlank() && campus.isNotBlank() && capacity > 0

    // 2. Sincronización: Cargar datos si se está editando
    LaunchedEffect(classroomToEdit) {
        if (classroomToEdit != null) {
            code = classroomToEdit.code
            capacityText = classroomToEdit.capacity.toString()
            campus = classroomToEdit.campus
        } else {
            code = ""
            capacityText = ""
            campus = ""
        }
    }

    Card(
        modifier = modifier.fillMaxHeight(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
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
                    text = if (classroomToEdit == null) "Registrar Nueva Aula" else "Editar Aula: ${classroomToEdit.code}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Campos de entrada
            OutlinedTextField(
                value = code,
                onValueChange = { code = it },
                label = { Text("Código de Aula") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )

            OutlinedTextField(
                value = capacityText,
                onValueChange = { capacityText = it.filter { char -> char.isDigit() } }, // Solo permite dígitos
                label = { Text("Capacidad") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(8.dp)
            )

            OutlinedTextField(
                value = campus,
                onValueChange = { campus = it },
                label = { Text("Sede (Campus)") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Botones de Acción
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Botón de Guardar/Registrar
                Button(
                    onClick = {
                        val classroomToSave = Classroom(
                            id = classroomToEdit?.id ?: 0L,
                            code = code.trim(),
                            capacity = capacity,
                            campus = campus.trim()
                        )
                        onSaveClassroom(classroomToSave)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    enabled = isFormValid
                ) {
                    Text(
                        text = if (classroomToEdit == null) "Registrar Aula" else "Guardar Cambios",
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                // Botón de Cancelar/Limpiar formulario
                if (classroomToEdit != null || code.isNotBlank() || capacityText.isNotBlank() || campus.isNotBlank()) {
                    OutlinedButton(
                        onClick = onClearForm,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
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
