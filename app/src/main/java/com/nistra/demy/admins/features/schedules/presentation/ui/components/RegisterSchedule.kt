package com.nistra.demy.admins.features.schedules.presentation.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.features.schedules.domain.models.*
import java.util.Locale

// Importaciones corregidas
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.DropdownMenuItem


@Composable
fun RegisterSchedule(
    modifier: Modifier = Modifier,
    scheduleToEdit: Schedule?,
    onSaveScheduleName: (String) -> Unit, // Guarda solo el nombre
    onClearForm: () -> Unit,
    onAddClassSession: (ClassSession) -> Unit,
    onDeleteClassSession: (ClassSession) -> Unit
) {
    var scheduleName by remember { mutableStateOf("") }
    val isEditing = scheduleToEdit != null

    // Sincronización: Cargar datos si se está editando
    LaunchedEffect(scheduleToEdit) {
        scheduleName = scheduleToEdit?.name ?: ""
    }

    Card(
        modifier = modifier.fillMaxHeight(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()), // Permite scroll en el panel izquierdo
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
                    text = if (!isEditing) "Registrar Nuevo Horario" else "Editar: ${scheduleToEdit!!.name}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Campo de Nombre y Botones de Schedule
            OutlinedTextField(
                value = scheduleName,
                onValueChange = { scheduleName = it },
                label = { Text("Nombre del Horario") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { onSaveScheduleName(scheduleName.trim()) },
                    modifier = Modifier.weight(if (isEditing) 1f else 2f).height(48.dp),
                    enabled = scheduleName.isNotBlank()
                ) {
                    Text(text = if (!isEditing) "Crear Horario" else "Actualizar Nombre")
                }

                if (isEditing || scheduleName.isNotBlank()) {
                    OutlinedButton(
                        onClick = onClearForm,
                        modifier = Modifier.weight(1f).height(48.dp)
                    ) {
                        Icon(Icons.Default.Clear, contentDescription = "Cancelar")
                        Spacer(Modifier.width(4.dp))
                        Text("Cancelar")
                    }
                }
            }

            // Contenido Adicional: Formulario/Lista de Sesiones (SOLO EN EDICIÓN)
            if (isEditing) {
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                ScheduleSessionsContent(
                    schedule = scheduleToEdit!!,
                    onAddClassSession = onAddClassSession,
                    onDeleteClassSession = onDeleteClassSession
                )
            }
        }
    }
}

@Composable
private fun ScheduleSessionsContent(
    schedule: Schedule,
    onAddClassSession: (ClassSession) -> Unit,
    onDeleteClassSession: (ClassSession) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Sesiones de Clase (${schedule.sessions.size})",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        )

        // Lista de Sesiones Existentes
        if (schedule.sessions.isNotEmpty()) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                schedule.sessions.forEach { session ->
                    ClassSessionListItem(session = session, onDelete = { onDeleteClassSession(session) })
                }
            }
        } else {
            Text("No hay sesiones registradas para este horario.", style = MaterialTheme.typography.bodyMedium)
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        // Formulario de Adición de Sesión
        AddClassSessionForm(onAddSession = onAddClassSession)
    }
}

@Composable
fun ClassSessionListItem(
    session: ClassSession,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${session.courseCode} (${session.courseName})",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${session.dayOfWeek.name.lowercase().capitalize(Locale.getDefault())} ${session.timeRange.startTime} - ${session.timeRange.endTime}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Aula: ${session.classroomCode} | Prof: ${session.teacherName}",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            IconButton(onClick = onDelete) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Eliminar Sesión",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

// =================================================================================
// FORMULARIO DE ADICIÓN DE SESIÓN (Incluye mocks para simular los Dropdowns)
// =================================================================================

@Composable
fun AddClassSessionForm(onAddSession: (ClassSession) -> Unit) {
    Text(
        "Añadir Nueva Sesión de Clase",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold,
    )
    Spacer(Modifier.height(8.dp))

    // Estados Locales para el Formulario
    var selectedDay by remember { mutableStateOf<DayOfWeek?>(null) }
    var startTime by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }
    var selectedCourseId by remember { mutableStateOf<Long?>(null) }
    var selectedClassroomId by remember { mutableStateOf<Long?>(null) }
    var selectedTeacherId by remember { mutableStateOf<Long?>(null) }
    var selectedTeacherName by remember { mutableStateOf("") }

    // --- MOCKS DE DATOS PARA DROPBOX ---
    val mockCourses = mapOf(101L to "KOT101 - Kotlin Básico", 102L to "JC205 - Compose Avanzado")
    val mockClassrooms = mapOf(201L to "A101 - Central", 202L to "B205 - Sur")
    val mockTeachers = mapOf(301L to "Juan Pérez", 302L to "Ana Gómez")
    // --- FIN MOCKS ---


    // 1. DÍA DE LA SEMANA (Dropdown)
    DayOfWeekDropdown(
        label = "Día de la Semana",
        selectedDay = selectedDay,
        onDaySelected = { selectedDay = it }
    )

    // 2. RANGO DE TIEMPO
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedTextField(
            value = startTime,
            onValueChange = { startTime = it },
            label = { Text("Hora Inicio (HH:mm)") },
            modifier = Modifier.weight(1f),
            singleLine = true
        )
        OutlinedTextField(
            value = endTime,
            onValueChange = { endTime = it },
            label = { Text("Hora Fin (HH:mm)") },
            modifier = Modifier.weight(1f),
            singleLine = true
        )
    }

    // 3. CURSO (Dropdown)
    DropdownMenu(
        label = "Curso Disponible",
        options = mockCourses,
        selectedId = selectedCourseId,
        onSelected = { id ->
            selectedCourseId = id
        }
    )

    // 4. AULA (Dropdown)
    DropdownMenu(
        label = "Aula/Classroom",
        options = mockClassrooms,
        selectedId = selectedClassroomId,
        onSelected = { id ->
            selectedClassroomId = id
        }
    )

    // 5. PROFESOR (Dropdown)
    DropdownMenu(
        label = "Profesor",
        options = mockTeachers,
        selectedId = selectedTeacherId,
        onSelected = { id ->
            selectedTeacherId = id
            selectedTeacherName = mockTeachers[id] ?: ""
        }
    )

    val isFormValid = selectedDay != null && startTime.isNotBlank() && endTime.isNotBlank() &&
        selectedCourseId != null && selectedClassroomId != null && selectedTeacherId != null

    Button(
        onClick = {
            if (isFormValid) {
                // Crear ClassSession con datos enriquecidos MOCK
                val session = ClassSession(
                    id = 0, // ID 0 para ser asignado en el repositorio (CREATE)
                    timeRange = TimeRange(startTime, endTime),
                    dayOfWeek = selectedDay!!,
                    courseId = selectedCourseId!!,
                    classroomId = selectedClassroomId!!,
                    teacherId = selectedTeacherId!!,
                    // Estos campos son enriquecidos/mockeados para la UI, el ViewModel los mapea.
                    courseName = mockCourses[selectedCourseId]?.split(" - ")?.getOrNull(1) ?: "",
                    courseCode = mockCourses[selectedCourseId]?.split(" - ")?.getOrNull(0) ?: "",
                    teacherName = selectedTeacherName, // Usamos el nombre completo para mapear a First/Last Name
                    classroomCode = mockClassrooms[selectedClassroomId]?.split(" - ")?.getOrNull(0) ?: "",
                    classroomCampus = mockClassrooms[selectedClassroomId]?.split(" - ")?.getOrNull(1) ?: "",
                    durationMinutes = 60 // Mockeado para simplificar
                )
                onAddSession(session)
            }
        },
        modifier = Modifier.fillMaxWidth().height(48.dp),
        enabled = isFormValid
        //colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {
        Icon(Icons.Default.Add, contentDescription = "Añadir Sesión")
        Spacer(Modifier.width(8.dp))
        Text("Añadir Sesión al Horario")
    }
}

// --- Componente auxiliar genérico para Dropdowns ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenu(
    label: String,
    options: Map<Long, String>,
    selectedId: Long?,
    onSelected: (Long) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = options[selectedId] ?: label,
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
