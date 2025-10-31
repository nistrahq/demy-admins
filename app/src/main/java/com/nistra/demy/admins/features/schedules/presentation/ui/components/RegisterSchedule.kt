package com.nistra.demy.admins.features.schedules.presentation.ui.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.features.classrooms.domain.models.Classroom
import com.nistra.demy.admins.features.schedules.domain.models.Schedule
import com.nistra.demy.admins.features.schedules.domain.models.ClassSession
import com.nistra.demy.admins.features.schedules.presentation.state.ScheduleUiState
import com.nistra.demy.admins.features.schedules.presentation.model.ClassSessionFormData
import com.nistra.demy.admins.features.teachers.domain.model.Teacher
import com.nistra.demy.admins.features.courses.domain.models.Course
import java.util.Locale

@Composable
fun RegisterSchedule(
    modifier: Modifier = Modifier,
    uiState: ScheduleUiState,
    onScheduleNameChange: (String) -> Unit,
    onSaveScheduleName: () -> Unit,
    onClearForm: () -> Unit,
    onSessionFormChange: (ClassSessionFormData) -> Unit,
    onAddClassSession: () -> Unit,
    onDeleteClassSession: (ClassSession) -> Unit
) {
    val scheduleToEdit = uiState.scheduleToEdit
    val isEditing = scheduleToEdit != null
    val scheduleName = uiState.nameForm.name
    val isScheduleFormLoading = uiState.isLoading
    val sessionFormLoading = uiState.isSessionFormLoading

    Card(
        modifier = modifier.fillMaxHeight(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Título
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = if (!isEditing) "Registrar Nuevo Horario" else "Editar: ${scheduleName}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Campo de Nombre y Botones de Schedule
            OutlinedTextField(
                value = scheduleName,
                onValueChange = onScheduleNameChange,
                label = { Text("Nombre del Horario") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                enabled = !isScheduleFormLoading
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = onSaveScheduleName,
                    modifier = Modifier.weight(if (isEditing) 1f else 2f).height(48.dp),
                    enabled = uiState.nameForm.isFormValid && !isScheduleFormLoading
                ) {
                    if (isScheduleFormLoading) {
                        CircularProgressIndicator(Modifier.size(20.dp))
                    } else {
                        Text(text = if (!isEditing) "Crear Horario" else "Actualizar Nombre")
                    }
                }

                if (isEditing || scheduleName.isNotBlank()) {
                    OutlinedButton(
                        onClick = onClearForm,
                        modifier = Modifier.weight(1f).height(48.dp),
                        enabled = !isScheduleFormLoading
                    ) {
                        Icon(Icons.Default.Clear, contentDescription = "Cancelar")
                        Spacer(Modifier.width(4.dp))
                        Text("Cancelar")
                    }
                }
            }

            // Mensaje de Error
            uiState.error?.let { errorMsg ->
                Text(text = errorMsg, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
            }

            // Contenido Adicional: Formulario/Lista de Sesiones (SOLO EN EDICIÓN)
            if (isEditing && scheduleToEdit != null) {
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                ScheduleSessionsContent(
                    schedule = scheduleToEdit,
                    formData = uiState.sessionForm,
                    availableCourses = uiState.availableCourses,
                    availableClassrooms = uiState.availableClassrooms,
                    availableTeachers = uiState.availableTeachers,
                    onSessionFormChange = onSessionFormChange,
                    onAddClassSession = onAddClassSession,
                    onDeleteClassSession = onDeleteClassSession,
                    isLoading = sessionFormLoading
                )
            }
        }
    }
}

// =================================================================================
// Subcomponentes del formulario principal
// =================================================================================

@Composable
private fun ScheduleSessionsContent(
    schedule: Schedule,
    formData: ClassSessionFormData,
    availableCourses: List<Course>,
    availableClassrooms: List<Classroom>, // Usar modelo Classroom
    availableTeachers: List<Teacher>,
    onSessionFormChange: (ClassSessionFormData) -> Unit,
    onAddClassSession: () -> Unit,
    onDeleteClassSession: (ClassSession) -> Unit,
    isLoading: Boolean
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Sesiones de Clase (${schedule.classSessions.size})",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        )

        // Lista de Sesiones Existentes
        if (schedule.classSessions.isNotEmpty()) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                schedule.classSessions.forEach { session ->
                    ClassSessionListItem(session = session, onDelete = { onDeleteClassSession(session) })
                }
            }
        } else {
            Text("No hay sesiones registradas para este horario.", style = MaterialTheme.typography.bodyMedium)
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        // Formulario de Adición de Sesión (Refactorizado)
        AddClassSessionForm(
            formData = formData,
            availableCourses = availableCourses,
            availableClassrooms = availableClassrooms,
            availableTeachers = availableTeachers,
            onFormChange = onSessionFormChange,
            onAddSession = onAddClassSession,
            isLoading = isLoading
        )
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
