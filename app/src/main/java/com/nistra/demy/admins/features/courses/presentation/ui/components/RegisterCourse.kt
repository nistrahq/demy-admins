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

@Composable
fun RegisterCourse(
    modifier: Modifier = Modifier,
    courseToEdit: Course?,
    onSaveCourse: (Course) -> Unit, // Recibe el modelo de dominio Course
    onClearForm: () -> Unit
) {
    // 1. Manejo del Estado Local del Formulario (solo campos del DTO y Model)
    var name by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    // 2. Sincronización: Cargar datos si se está editando un curso
    LaunchedEffect(courseToEdit) {
        if (courseToEdit != null) {
            name = courseToEdit.name
            code = courseToEdit.code
            description = courseToEdit.description
        } else {
            // Limpiar formulario si courseToEdit es null
            name = ""
            code = ""
            description = ""
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
                    text = if (courseToEdit == null) "Registrar Nuevo Curso" else "Editar Curso: ${courseToEdit.code}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Campos de entrada - MODIFICACIÓN A CADA CAMPO EN SU PROPIA FILA
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre del Curso") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true, // Fuerza una sola línea
                shape = RoundedCornerShape(8.dp)
            )

            OutlinedTextField(
                value = code,
                onValueChange = { code = it },
                label = { Text("Código de Curso") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true, // Fuerza una sola línea
                shape = RoundedCornerShape(8.dp)
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 4, // Mantiene el tamaño fijo para la descripción
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Botones de Acción - MODIFICACIÓN PARA CENTRAR EL BOTÓN PRINCIPAL
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Botón de Guardar/Registrar
                Button(
                    onClick = {
                        // Construye el Domain Model (Course)
                        val courseToSave = Course(
                            // Se usa 0L para un curso nuevo; el ID real es provisto al editar
                            id = courseToEdit?.id ?: 0L,
                            name = name.trim(),
                            code = code.trim(),
                            description = description.trim()
                        )
                        // Envía el Domain Model (Course) al ViewModel
                        onSaveCourse(courseToSave)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    // Habilitado solo si los campos clave están llenos
                    enabled = name.isNotBlank() && code.isNotBlank() && description.isNotBlank()
                ) {
                    Text(
                        text = if (courseToEdit == null) "Registrar Curso" else "Guardar Cambios",
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                // Botón de Cancelar/Limpiar formulario (condicional)
                if (courseToEdit != null || name.isNotBlank() || code.isNotBlank() || description.isNotBlank()) {
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
