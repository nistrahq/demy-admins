package com.nistra.demy.admins.features.schedules.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nistra.demy.admins.features.schedules.presentation.ui.components.RegisterSchedule
import com.nistra.demy.admins.features.schedules.presentation.ui.components.ScheduleList
import com.nistra.demy.admins.features.schedules.presentation.viewmodel.SchedulesViewModel

@Composable
fun SchedulesScreen(
    // La navegación a la pantalla de detalle de horarios (viewer) se agregaría aquí
    viewModel: SchedulesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // Contenedor principal para el diseño de dos paneles 50/50
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Panel Izquierdo: Formulario de Registro/Edición (50% del ancho)
            RegisterSchedule(
                modifier = Modifier.weight(0.5f),
                uiState = uiState,
                onScheduleNameChange = viewModel::onScheduleNameChange,
                onSaveScheduleName = viewModel::saveScheduleName,
                onClearForm = viewModel::clearSelectedSchedule,
                onSessionFormChange = viewModel::onSessionFormChange,
                onAddClassSession = viewModel::addClassSession,
                onDeleteClassSession = viewModel::deleteClassSession,
                // onNavigateToViewer se podría pasar aquí si fuera necesario ver el Schedule en otra vista
            )

            // Panel Derecho: Lista de Schedules (50% del ancho)
            ScheduleList(
                modifier = Modifier.weight(0.5f),
                schedules = uiState.schedules,
                searchQuery = uiState.searchQuery,
                onScheduleSelected = viewModel::selectScheduleForEdit,
                onDeleteSchedule = viewModel::deleteSchedule,
                onSearchQueryChange = viewModel::searchSchedules,
                isLoading = uiState.isLoading
            )
        }
    }
}
