package com.nistra.demy.admins.features.schedules.presentation.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nistra.demy.admins.core.design.theme.DemyTheme
import com.nistra.demy.admins.core.ui.layout.main.MainDestination
import com.nistra.demy.admins.core.ui.preview.MainLayoutPreviewContainer
import com.nistra.demy.admins.core.ui.preview.TabletPreviewLight
import com.nistra.demy.admins.features.schedules.domain.models.ClassSession
import com.nistra.demy.admins.features.schedules.domain.models.Schedule
import com.nistra.demy.admins.features.schedules.domain.models.TimeRange

// 🚨 IMPORTAR REPOSITORIO FALSO para el Preview
import com.nistra.demy.admins.features.schedules.data.repositories.FakeScheduleRepository
import com.nistra.demy.admins.features.schedules.presentation.viewmodel.ScheduleViewerViewModel

// ====================================================================
// CONSTANTES Y UTILIDADES DE UI
// ====================================================================

private val HOUR_HEIGHT: Dp = 80.dp
private val START_HOUR = 7
private val END_HOUR = 20
private val LIGHT_GRID_COLOR_ALPHA = 0.15f

private val DAY_MAPPING = linkedMapOf(
    "MONDAY" to "LUNES",
    "TUESDAY" to "MARTES",
    "WEDNESDAY" to "MIÉRCOLES",
    "THURSDAY" to "JUEVES",
    "FRIDAY" to "VIERNES",
    "SATURDAY" to "SÁBADO",
    "SUNDAY" to "DOMINGO"
)

// Colores para los eventos
val eventColors = listOf(
    Color(0xFFB1EEFF), // Light Green
    Color(0xFFFFCDDF), // Pink
    Color(0xFFC4CBFF), // Teal
    Color(0xFFD9FAC4), // Indigo
    Color(0xFFFFEABC), // Orange Pastel
)

fun timeStringToMinutes(timeStr: String): Int {
    return try {
        val parts = timeStr.split(":")
        val hours = parts.getOrElse(0) { "0" }.toInt()
        val minutes = parts.getOrElse(1) { "0" }.toInt()
        hours * 60 + minutes
    } catch (e: Exception) { 0 }
}

fun formatHour(hour: Int): String {
    return if (hour >= 13) "${hour - 12} PM" else "$hour AM"
}

// 🚨 ELIMINAMOS ClassEvent y su mapeador. Usamos ClassSession directamente.

// ====================================================================
// FUNCIÓN PRINCIPAL DE LA PANTALLA
// ====================================================================

@Composable
fun ScheduleViewerScreen(
    modifier: Modifier = Modifier,
    viewModel: ScheduleViewerViewModel = hiltViewModel()
) {
    val allSchedules by viewModel.allSchedules.collectAsState()
    val selectedSchedule by viewModel.selectedSchedule.collectAsState()
    val selectedScheduleName by viewModel.selectedScheduleName.collectAsState()

    // Solo mostramos hasta el VIERNES ya que la UI parece enfocada en días laborales.
    // Si la lista de días viene del backend/domain, usarla completa. Aquí se usa la keys del map.
    val days = DAY_MAPPING.keys.toList()
    val scrollState = rememberScrollState()

    Column(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
//            // Título principal
//            Text(
//                text = "Visor de Horarios",
//                style = MaterialTheme.typography.headlineMedium,
//                fontWeight = FontWeight.Bold,
//                color = MaterialTheme.colorScheme.onSurface
//            )
            // 1. DROPBOX (Selector de Horario)
            ScheduleDropdown(
                allSchedules = allSchedules,
                selectedScheduleName = selectedScheduleName,
                onScheduleSelected = viewModel::selectSchedule,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // 2. HORARIO SEMANAL (Grid)
        ScheduleLayout(
            schedule = selectedSchedule,
            days = days,
            scrollState = scrollState,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
        )
    }
}

// ... ScheduleDropdown, ScheduleLayout, ScheduleHeader, TimeColumn (Sin cambios significativos) ...

@Composable
fun ScheduleDropdown(
    allSchedules: List<Schedule>,
    selectedScheduleName: String,
    onScheduleSelected: (Long, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier.wrapContentSize(Alignment.TopStart)) {
        OutlinedTextField(
            value = selectedScheduleName,
            onValueChange = {},
            label = { Text("Horario Seleccionado") },
            readOnly = true,
            shape = RoundedCornerShape(8.dp),
            trailingIcon = {
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = "Expandir Horarios",
                    Modifier.clickable { expanded = true }
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            allSchedules.forEach { schedule ->
                DropdownMenuItem(
                    text = {
                        Text(
                            schedule.name,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    onClick = {
                        onScheduleSelected(schedule.id, schedule.name)
                        expanded = false
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}


@Composable
fun ScheduleLayout(
    schedule: Schedule?,
    days: List<String>,
    scrollState: ScrollState,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        if (schedule != null) {
            ScheduleHeader(days = days)

            Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                TimeColumn(modifier = Modifier.width(60.dp), scrollState = scrollState)

                ScheduleGrid(
                    schedule = schedule,
                    days = days,
                    scrollState = scrollState,
                    modifier = Modifier.weight(1f)
                )
            }
        } else {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    "Cargando o no se encontró ningún horario.",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun ScheduleHeader(days: List<String>, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.width(60.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Hora", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onPrimary)
        }

        days.forEach { dayBackendName ->
            val dayDisplayName = DAY_MAPPING[dayBackendName] ?: dayBackendName

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = dayDisplayName,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun TimeColumn(modifier: Modifier = Modifier, scrollState: ScrollState) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.End
    ) {
        Spacer(modifier = Modifier.height(HOUR_HEIGHT / 2))

        for (i in START_HOUR + 1..END_HOUR) {
            Box(
                modifier = Modifier
                    .height(HOUR_HEIGHT)
                    .fillMaxWidth()
                    .padding(end = 4.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                Text(
                    text = formatHour(i - 1),
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}


@Composable
fun ScheduleGrid(
    schedule: Schedule,
    days: List<String>,
    modifier: Modifier = Modifier,
    scrollState: ScrollState
) {

    // 🚨 CAMBIO: Usamos el modelo de dominio directamente
    val allSessions = remember(schedule.sessions) {
        schedule.sessions
    }

    val sessionsGroupedByDay = remember(allSessions) {
        // Agrupamos por el nombre (String) del día para el mapeo de días
        allSessions.groupBy { it.dayOfWeek.name }
    }

    Row(
        modifier = modifier
            .verticalScroll(scrollState)
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        days.forEach { dayBackendName ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .border(
                        width = 0.5.dp,
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = LIGHT_GRID_COLOR_ALPHA)
                    )
            ) {
                VerticalGridLines()

                // 🚨 CAMBIO: Accedemos a las ClassSession
                val daySessions = sessionsGroupedByDay[dayBackendName] ?: emptyList()
                daySessions.forEach { session ->
                    val eventColor = eventColors[(session.id % eventColors.size).toInt()]
                    // 🚨 CAMBIO: Pasamos ClassSession a ClassCard
                    ClassCard(session, eventColor = eventColor)
                }
            }
        }
    }
}

@Composable
fun VerticalGridLines(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxHeight()) {
        Spacer(modifier = Modifier.height(HOUR_HEIGHT / 2))

        val minutesPerLine = 30
        val slotsPerHour = 60 / minutesPerLine
        val lineCount = (END_HOUR - START_HOUR) * slotsPerHour
        val slotHeight = HOUR_HEIGHT / slotsPerHour

        repeat(lineCount) { index ->
            val isHourLine = index % slotsPerHour == 0
            Divider(
                modifier = Modifier.height(slotHeight).fillMaxWidth(),
                thickness = if (isHourLine) 1.dp else 0.5.dp,
                color = if (isHourLine) MaterialTheme.colorScheme.outline.copy(alpha = LIGHT_GRID_COLOR_ALPHA) else MaterialTheme.colorScheme.outlineVariant.copy(alpha = LIGHT_GRID_COLOR_ALPHA)
            )
        }
    }
}


@Composable
fun ClassCard(session: ClassSession, modifier: Modifier = Modifier, eventColor: Color) {

    val density = LocalDensity.current

    // Usamos timeRange.startTime de ClassSession
    val eventStartTotalMinutes = timeStringToMinutes(session.timeRange.startTime)
    val calendarStartTotalMinutes = START_HOUR * 60
    val offsetMinutes = eventStartTotalMinutes - calendarStartTotalMinutes

    val dpPerMinute = with(density) { HOUR_HEIGHT.toPx() / 60 }

    val offsetDp = with(density) { (offsetMinutes * dpPerMinute).toDp() }
    // Usamos durationMinutes de ClassSession
    val heightDp = with(density) { (session.durationMinutes * dpPerMinute).toDp() }

    val endHourMinutes = eventStartTotalMinutes + session.durationMinutes
    val endHourFormatted = "${(endHourMinutes / 60).toInt().toString().padStart(2, '0')}:${(endHourMinutes % 60).toString().padStart(2, '0')}"

    val textColor = Color.Black.copy(alpha = 0.95f)

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = eventColor.copy(alpha = 0.95f)
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 2.dp)
            .offset(y = offsetDp)
            .height(heightDp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            // Indicador de color vertical
            Spacer(
                modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .background(eventColor.copy(alpha = 1.0f))
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // 1. Título, Código y Hora (Jerarquía Alta)
                Column {
                    // Usamos courseName de ClassSession
                    Text(
                        text = session.courseName,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = textColor,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
//                    // Usamos courseCode de ClassSession
//                    Text(
//                        text = session.courseCode,
//                        style = MaterialTheme.typography.labelSmall,
//                        color = textColor.copy(alpha = 0.9f),
//                        fontWeight = FontWeight.SemiBold
//                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        // Usamos timeRange.startTime de ClassSession
                        text = "${session.timeRange.startTime} - ${endHourFormatted}",
                        style = MaterialTheme.typography.labelSmall,
                        color = textColor.copy(alpha = 0.7f)
                    )
                }

                // 2. Aula y Profesor (Info Secundaria Compacta con Iconos)
                Column(horizontalAlignment = Alignment.Start) {
                    // Aula (Ubicación y Campus)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Aula",
                            tint = textColor.copy(alpha = 0.7f),
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        // Usamos classroomCode y classroomCampus de ClassSession
                        Text(
                            text = "${session.classroomCode} - ${session.classroomCampus}",
                            style = MaterialTheme.typography.labelSmall,
                            color = textColor.copy(alpha = 0.7f),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    // Profesor
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profesor",
                            tint = textColor.copy(alpha = 0.7f),
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        // Usamos teacherName de ClassSession
                        Text(
                            text = session.teacherName,
                            style = MaterialTheme.typography.labelSmall,
                            color = textColor.copy(alpha = 0.7f),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun ScheduleViewerScreenPreview() {

    MainLayoutPreviewContainer(title = "Schedule Viewer", selectedDestinationId = MainDestination.Schedules.id) {
        DemyTheme {
            ScheduleViewerScreen()
        }
    }
}
