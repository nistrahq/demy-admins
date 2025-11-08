// admins/features/courses/presentation/ui/CoursesScreen.kt
package com.nistra.demy.admins.features.courses.presentation.ui

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
import com.nistra.demy.admins.features.courses.presentation.ui.components.RegisterCourse
import com.nistra.demy.admins.features.courses.presentation.viewmodel.CoursesViewModel

@Composable
fun CoursesScreen(
    viewModel: CoursesViewModel = hiltViewModel()
) {
    // Recoge el estado de la UI (CourseUiState) del ViewModel
    val uiState by viewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // Contenedor principal para el diseño de dos paneles
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            RegisterCourse(
                modifier = Modifier.weight(0.50f),
                courseToEdit = uiState.courseToEdit,
                onSaveCourse = viewModel::saveCourse, // Función que acepta un objeto Course
                onClearForm = viewModel::clearSelectedCourse // Limpia el formulario y resetea courseToEdit a null
            )

            CourseList(
                modifier = Modifier.weight(0.50f),
                courses = uiState.courses,
                onCourseSelected = viewModel::selectCourseForEdit, // Al seleccionar, carga el curso en el otro panel
                onDeleteCourse = viewModel::deleteCourse, // Función para eliminar
                onSearchQueryChange = viewModel::searchCourses,
                searchQuery = uiState.searchQuery
            )
        }
    }
}

@Preview
@Composable
fun CoursesScreenPreview(){

}
