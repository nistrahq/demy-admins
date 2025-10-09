package com.nistra.demy.admins.features.classrooms.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.features.classrooms.domain.models.Classroom
import com.nistra.demy.admins.features.classrooms.domain.repositories.ClassroomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.update

// 1. Define el estado de la UI para manejar todos los datos de la pantalla
data class ClassroomUiState(
    val classrooms: List<Classroom> = emptyList(),
    val classroomToEdit: Classroom? = null,
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class ClassroomsViewModel @Inject constructor(
    private val repository: ClassroomRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ClassroomUiState())
    val uiState: StateFlow<ClassroomUiState> = _uiState.asStateFlow()

    // Lista completa de aulas (para búsquedas)
    private var allClassroomsCache: List<Classroom> = emptyList()


    init {
        getAllClassrooms()
    }

    // =========================================================================
    // 2. Lógica de Carga y Búsqueda
    // =========================================================================

    private fun getAllClassrooms() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val fetchedClassrooms = repository.getAllClassrooms()
                allClassroomsCache = fetchedClassrooms // Almacena el caché completo
                // Aplica la búsqueda actual al recargar si hay una query
                val filteredList = if (_uiState.value.searchQuery.isBlank()) {
                    fetchedClassrooms
                } else {
                    fetchedClassrooms.filter { classroom ->
                        classroom.code.contains(_uiState.value.searchQuery, ignoreCase = true) ||
                            classroom.campus.contains(_uiState.value.searchQuery, ignoreCase = true)
                    }
                }

                _uiState.update {
                    it.copy(
                        classrooms = filteredList,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = "Error al cargar aulas.") }
            }
        }
    }

    fun searchClassrooms(query: String) {
        _uiState.update { it.copy(searchQuery = query) }

        // Filtra la lista en memoria (desde el caché) por código o sede (campus)
        val filteredList = if (query.isBlank()) {
            allClassroomsCache
        } else {
            allClassroomsCache.filter { classroom ->
                classroom.code.contains(query, ignoreCase = true) ||
                    classroom.campus.contains(query, ignoreCase = true)
            }
        }
        _uiState.update { it.copy(classrooms = filteredList) }
    }


    // =========================================================================
    // 3. Lógica de CRUD (Guardar/Editar/Eliminar)
    // =========================================================================

    fun saveClassroom(classroom: Classroom) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                if (classroom.id == 0L) {
                    // Nuevo aula
                    repository.createClassroom(
                        code = classroom.code,
                        capacity = classroom.capacity,
                        campus = classroom.campus
                    )
                } else {
                    // Edición
                    repository.updateClassroom(
                        id = classroom.id,
                        code = classroom.code,
                        capacity = classroom.capacity,
                        campus = classroom.campus
                    )
                }

                // Limpia el formulario y recarga la lista
                clearSelectedClassroom()
                getAllClassrooms()

            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = "Error al guardar el aula.") }
            }
        }
    }

    fun deleteClassroom(classroom: Classroom) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                repository.deleteClassroom(classroom.id)
                getAllClassrooms()
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = "Error al eliminar el aula.") }
            }
        }
    }

    // =========================================================================
    // 4. Lógica de Interacción con la UI (Selección de Aula)
    // =========================================================================

    fun selectClassroomForEdit(classroom: Classroom) {
        _uiState.update { it.copy(classroomToEdit = classroom) }
    }

    fun clearSelectedClassroom() {
        _uiState.update { it.copy(classroomToEdit = null) }
    }
}
