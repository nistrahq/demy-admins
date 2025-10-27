package com.nistra.demy.admins.features.courses.presentation.ui.screens

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
import com.nistra.demy.admins.features.courses.presentation.ui.components.CourseList
import com.nistra.demy.admins.features.courses.presentation.ui.components.CourseSectionDescription
import com.nistra.demy.admins.features.courses.presentation.ui.components.RegisterCourse
import com.nistra.demy.admins.features.courses.presentation.viewmodel.CoursesViewModel


@Composable
fun CoursesScreen(
    viewModel: CoursesViewModel = hiltViewModel()
) {
    // 1. Recolección de Estado de la capa de Presentación
    val uiState by viewModel.uiState.collectAsState()
    val formData by viewModel.formData.collectAsState()


        // Contenedor principal para el diseño de dos paneles 50/50
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Panel Izquierdo: Formulario de Registro
            RegisterCourse(
                modifier = Modifier.weight(0.50f),
                courseToEdit = uiState.courseToEdit,
                formData = formData,
                isLoading = uiState.isLoading,
                errorMessage = uiState.errorMessage,
                // Mapeo de eventos a funciones descriptivas del ViewModel
                onFormChange = viewModel::onCourseFormChange,
                onSaveCourseClick = viewModel::onSaveCourseClick,
                onClearFormClick = viewModel::onClearFormClick
            )

            // Panel Derecho: Lista de Cursos
            CourseList(
                modifier = Modifier.weight(0.50f),
                courses = uiState.courses,
                // Mapeo de eventos de la lista
                onCourseSelected = viewModel::onCourseSelectedForEdit,
                onDeleteCourse = viewModel::onDeleteCourseClick,
                onSearchQueryChange = viewModel::searchCourses,
                searchQuery = uiState.searchQuery
            )
        }

}
