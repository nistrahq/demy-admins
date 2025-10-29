package com.nistra.demy.admins.features.schedules.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.features.schedules.data.remote.models.AddClassSessionRequestDto
import com.nistra.demy.admins.features.schedules.domain.models.ClassSession
import com.nistra.demy.admins.features.schedules.domain.models.Schedule
import com.nistra.demy.admins.features.schedules.domain.repository.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// Estado de la UI
data class ScheduleUiState(
    val schedules: List<Schedule> = emptyList(),
    val scheduleToEdit: Schedule? = null, // Schedule completo seleccionado
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class SchedulesViewModel @Inject constructor(
    private val repository: ScheduleRepository // Asume que inyecta FakeScheduleRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ScheduleUiState())
    val uiState: StateFlow<ScheduleUiState> = _uiState.asStateFlow()

    private var allSchedulesCache: List<Schedule> = emptyList()

    init {
        getAllSchedules()
    }


    // Mapea el modelo de dominio a la solicitud de la capa de datos.
    private fun ClassSession.toAddRequest(): AddClassSessionRequestDto {
        // Lógica simple para dividir el nombre completo en nombre y apellido.
        val parts = teacherName.trim().split(" ", limit = 2)
        val firstName = parts.getOrElse(0) { "" }
        val lastName = parts.getOrElse(1) { "" }

        return AddClassSessionRequestDto(
            startTime = timeRange.startTime,
            endTime = timeRange.endTime,
            dayOfWeek = dayOfWeek.name, // Enum DayOfWeek a String
            courseId = courseId,
            classroomId = classroomId,
            teacherFirstName = firstName,
            teacherLastName = lastName
        )
    }

    // =========================================================================
    // Lógica de Carga y Búsqueda
    // =========================================================================

    private fun getAllSchedules() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val fetchedSchedules = repository.getAllSchedules()
                allSchedulesCache = fetchedSchedules

                val filteredList = fetchedSchedules.filter { it.name.contains(_uiState.value.searchQuery, ignoreCase = true) }

                _uiState.update { it.copy(
                    schedules = filteredList,
                    isLoading = false
                )}
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = "Error al cargar horarios.") }
            }
        }
    }

    fun searchSchedules(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        val filteredList = if (query.isBlank()) {
            allSchedulesCache
        } else {
            allSchedulesCache.filter { schedule ->
                schedule.name.contains(query, ignoreCase = true)
            }
        }
        _uiState.update { it.copy(schedules = filteredList) }
    }

    // =========================================================================
    // Lógica de CRUD para Schedule (El Contenedor Principal)
    // =========================================================================

    fun saveScheduleName(name: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val schedule = _uiState.value.scheduleToEdit
            try {
                val result = if (schedule == null) {
                    // Nuevo Schedule
                    repository.createSchedule(name)
                } else {
                    // Edición de Nombre
                    repository.updateScheduleName(schedule.id, name)
                }

                result?.let { selectScheduleForEdit(it) }
                getAllSchedules()
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = "Error al guardar el horario.") }
            }
        }
    }

    fun deleteSchedule(schedule: Schedule) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                repository.deleteSchedule(schedule.id)
                clearSelectedSchedule()
                getAllSchedules()
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = "Error al eliminar el horario.") }
            }
        }
    }

    // =========================================================================
    // Lógica CRUD para ClassSession (Elementos Anidados)
    // =========================================================================

    fun addClassSession(session: ClassSession) {
//        viewModelScope.launch {
//            val schedule = _uiState.value.scheduleToEdit ?: return@launch
//            _uiState.update { it.copy(isLoading = true, error = null) }
//
//            try {
//
//                repository.addClassSessionToSchedule(schedule.id, session.toAddRequest())?.let {
//                    _uiState.update { state -> state.copy(
//                        scheduleToEdit = it, // Actualizar el schedule en edición con la nueva sesión
//                        isLoading = false
//                    ) }
//                }
//                getAllSchedules() // Recargar la lista principal
//            } catch (e: Exception) {
//                _uiState.update { it.copy(isLoading = false, error = "Error al añadir sesión.") }
//            }
//        }
    }

    fun deleteClassSession(session: ClassSession) {
        viewModelScope.launch {
            val scheduleId = _uiState.value.scheduleToEdit?.id ?: return@launch
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                repository.removeClassSessionFromSchedule(scheduleId, session.id)

                // Actualizar el estado local (asumimos éxito)
                val updatedSessions = _uiState.value.scheduleToEdit!!.sessions.filter { it.id != session.id }
                _uiState.update { state -> state.copy(
                    scheduleToEdit = state.scheduleToEdit?.copy(sessions = updatedSessions),
                    isLoading = false
                )}
                getAllSchedules() // Recargar la lista principal

            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = "Error al eliminar sesión.") }
            }
        }
    }

    // =========================================================================
    // Lógica de Interacción con la UI
    // =========================================================================

    fun selectScheduleForEdit(schedule: Schedule) {
        _uiState.update { it.copy(scheduleToEdit = schedule) }
    }

    fun clearSelectedSchedule() {
        _uiState.update { it.copy(scheduleToEdit = null) }
    }
}
