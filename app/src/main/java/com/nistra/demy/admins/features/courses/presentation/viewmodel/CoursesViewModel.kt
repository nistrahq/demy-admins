package com.nistra.demy.admins.features.courses.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.features.courses.domain.models.Course
import com.nistra.demy.admins.features.courses.domain.repositories.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// 1. Define el estado de la UI para manejar todos los datos de la pantalla
data class CourseUiState(
    val courses: List<Course> = emptyList(),
    val courseToEdit: Course? = null,
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class CoursesViewModel @Inject constructor(
    private val repository: CourseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CourseUiState())
    val uiState: StateFlow<CourseUiState> = _uiState.asStateFlow()

    // Lista completa de cursos (para búsquedas)
    private var allCoursesCache: List<Course> = emptyList()


    init {
        getAllCourses()
    }

    // =========================================================================
    // 2. Lógica de Carga y Búsqueda
    // =========================================================================

    private fun getAllCourses() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val fetchedCourses = repository.getAllCourses()
                allCoursesCache = fetchedCourses // Almacena el caché completo
                _uiState.update {
                    it.copy(
                        courses = fetchedCourses,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = "Error al cargar cursos.") }
            }
        }
    }

    fun searchCourses(query: String) {
        _uiState.update { it.copy(searchQuery = query) }

        // Filtra la lista en memoria (desde el caché)
        val filteredList = if (query.isBlank()) {
            allCoursesCache
        } else {
            allCoursesCache.filter { course ->
                course.name.contains(query, ignoreCase = true) ||
                    course.code.contains(query, ignoreCase = true)
            }
        }
        _uiState.update { it.copy(courses = filteredList) }
    }


    // =========================================================================
    // 3. Lógica de CRUD (Guardar/Editar/Eliminar)
    // =========================================================================

    fun saveCourse(course: Course) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            // NO necesitamos crear el DTO aquí. Simplemente extraemos los campos
            // que el Repositorio necesita (name, code, description).

            try {
                if (course.id == 0L) {
                    // Nuevo curso: Llama a createCourse con campos individuales
                    repository.createCourse(
                        name = course.name,
                        code = course.code,
                        description = course.description
                    )
                } else {
                    // Edición: Llama a updateCourse con el ID y campos individuales
                    repository.updateCourse(
                        id = course.id,
                        name = course.name,
                        code = course.code,
                        description = course.description
                    )
                }

                // Limpia el formulario y recarga la lista
                clearSelectedCourse()
                getAllCourses()

            } catch (e: Exception) {
                // ... manejo de error
                _uiState.update { it.copy(isLoading = false, error = "Error al guardar el curso.") }
            }
        }
    }

    fun deleteCourse(course: Course) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                repository.deleteCourse(course.id)
                getAllCourses()
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = "Error al eliminar el curso.") }
            }
        }
    }

    // =========================================================================
    // 4. Lógica de Interacción con la UI (Selección de Curso)
    // =========================================================================

    fun selectCourseForEdit(course: Course) {
        _uiState.update { it.copy(courseToEdit = course) }
    }

    fun clearSelectedCourse() {
        _uiState.update { it.copy(courseToEdit = null) }
    }
}
