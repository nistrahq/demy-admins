package com.nistra.demy.admins.features.schedules.presentation.ui.components
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.features.schedules.domain.models.Schedule

@Composable
fun ScheduleList(
    modifier: Modifier = Modifier,
    schedules: List<Schedule>,
    onScheduleSelected: (Schedule) -> Unit,
    onDeleteSchedule: (Schedule) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    searchQuery: String,
    isLoading: Boolean // Agregamos el estado de carga
) {
    Card(
        modifier = modifier.fillMaxHeight(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                label = { Text("Buscar horarios por nombre") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                enabled = !isLoading
            )

            if (isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            if (schedules.isEmpty() && !isLoading) {
                Text("No hay agendas disponibles.", modifier = Modifier.padding(top = 16.dp))
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(schedules, key = { it.id }) { schedule ->
                    ScheduleListItem(
                        schedule = schedule,
                        onEditClick = { onScheduleSelected(schedule) },
                        onDeleteClick = { onDeleteSchedule(schedule) }
                    )
                }
            }
        }
    }
}

@Composable
fun ScheduleListItem(
    schedule: Schedule,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                // Al hacer clic en el item, se selecciona para editar en el panel izquierdo.
                .clickable { onEditClick() }
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Información del Horario
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = schedule.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Sesiones: ${schedule.classSessions.size}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.width(12.dp))

            // Acciones: Editar y Eliminar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                IconButton(onClick = onEditClick) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Editar horario",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Eliminar horario",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}
