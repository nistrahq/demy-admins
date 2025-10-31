package com.nistra.demy.admins.features.schedules.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.features.classrooms.domain.models.Classroom
import com.nistra.demy.admins.features.schedules.domain.models.DayOfWeek
import com.nistra.demy.admins.features.schedules.presentation.model.ClassSessionFormData
import com.nistra.demy.admins.features.courses.domain.models.Course
import com.nistra.demy.admins.features.teachers.domain.model.Teacher
import java.util.Locale

@Composable
fun AddClassSessionForm(
    formData: ClassSessionFormData,
    availableCourses: List<Course>,
    availableClassrooms: List<Classroom>,
    availableTeachers: List<Teacher>,
    onFormChange: (ClassSessionFormData) -> Unit,
    onAddSession: () -> Unit,
    isLoading: Boolean
) {
    Text(
        "Añadir Nueva Sesión de Clase",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold,
    )
    Spacer(Modifier.height(8.dp))

    val teacherOptions = availableTeachers.associate { it.id to "${it.firstName} ${it.lastName}" }
    val courseOptions = availableCourses.associate { it.id to "${it.code} - ${it.name}" }
    val classroomOptions = availableClassrooms.associate { it.id to "C${it.code} - ${it.campus}" }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

        // 1. DÍA DE LA SEMANA (Dropdown)
        DayOfWeekDropdown(
            label = "Día de la Semana",
            selectedDay = DayOfWeek.entries.find { it.name == formData.selectedDay },
            onDaySelected = { day -> onFormChange(formData.copy(selectedDay = day.name)) }
        )

        // 2. RANGO DE TIEMPO
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            OutlinedTextField(
                value = formData.startTime,
                onValueChange = { onFormChange(formData.copy(startTime = it)) },
                label = { Text("Hora Inicio (HH:mm)") },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
            OutlinedTextField(
                value = formData.endTime,
                onValueChange = { onFormChange(formData.copy(endTime = it)) },
                label = { Text("Hora Fin (HH:mm)") },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
        }

        // 3. CURSO (Dropdown)
        DropdownMenu(
            label = "Curso Disponible",
            options = courseOptions,
            selectedId = formData.courseId,
            onSelected = { id -> onFormChange(formData.copy(courseId = id)) }
        )

        // 4. AULA (Dropdown)
        DropdownMenu(
            label = "Aula/Classroom",
            options = classroomOptions,
            selectedId = formData.classroomId,
            onSelected = { id -> onFormChange(formData.copy(classroomId = id)) }
        )

        // 5. PROFESOR (Dropdown)
        DropdownMenu(
            label = "Profesor",
            options = teacherOptions,
            selectedId = formData.teacherId,
            onSelected = { id -> onFormChange(formData.copy(teacherId = id)) }
        )

        Button(
            onClick = onAddSession,
            modifier = Modifier.fillMaxWidth().height(48.dp),
            enabled = formData.isFormValid && !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(Modifier.size(20.dp), color = MaterialTheme.colorScheme.onPrimary)
            } else {
                Icon(Icons.Default.Add, contentDescription = "Añadir Sesión")
                Spacer(Modifier.width(8.dp))
                Text("Añadir Sesión al Horario")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropdownMenu( // <-- Se añadió el tipo genérico <T>
    label: String,
    options: Map<T, String>,
    selectedId: T?,
    onSelected: (T) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val selectedName = options[selectedId] ?: label

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedName,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor().fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { (id, name) ->
                DropdownMenuItem(
                    text = { Text(name) },
                    onClick = {
                        onSelected(id)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}


// --- Componente auxiliar específico para Días de la Semana ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayOfWeekDropdown(
    label: String,
    selectedDay: DayOfWeek?,
    onDaySelected: (DayOfWeek) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedDay?.name?.lowercase()?.capitalize(Locale.getDefault()) ?: label,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor().fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DayOfWeek.entries.forEach { day ->
                DropdownMenuItem(
                    text = { Text(day.name.lowercase().capitalize(Locale.getDefault())) },
                    onClick = {
                        onDaySelected(day)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}
