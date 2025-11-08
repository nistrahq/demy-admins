package com.nistra.demy.admins.features.classrooms.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

// Importa tus componentes de UI
import com.nistra.demy.admins.features.classrooms.presentation.ui.components.ClassroomList
import com.nistra.demy.admins.features.classrooms.presentation.ui.components.RegisterClassroom
// Importa tu ViewModel
import com.nistra.demy.admins.features.classrooms.presentation.viewmodel.ClassroomsViewModel

@Composable
fun ClassroomsScreen(
    // Inyección del ViewModel (asumiendo Hilt)
    viewModel: ClassroomsViewModel = hiltViewModel()
) {
    // Recoge el estado de la UI (ClassroomUiState) del ViewModel
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
            RegisterClassroom(
                modifier = Modifier.weight(0.5f),
                classroomToEdit = uiState.classroomToEdit,
                onSaveClassroom = viewModel::saveClassroom,
                onClearForm = viewModel::clearSelectedClassroom
            )

            // Panel Derecho: Lista de Aulas (50% del ancho)
            ClassroomList(
                modifier = Modifier.weight(0.5f),
                classrooms = uiState.classrooms,
                onClassroomSelected = viewModel::selectClassroomForEdit,
                onDeleteClassroom = viewModel::deleteClassroom,
                onSearchQueryChange = viewModel::searchClassrooms,
                searchQuery = uiState.searchQuery
            )
        }
    }
}

@Preview
@Composable
fun ClassroomsScreenPreview(){
    // La previsualización de la pantalla ya utiliza el diseño 50/50
}
