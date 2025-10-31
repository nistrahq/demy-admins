package com.nistra.demy.admins.features.schedules.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.features.schedules.domain.models.Schedule
import com.nistra.demy.admins.features.schedules.domain.models.ClassSession
import com.nistra.demy.admins.features.schedules.domain.usecase.* // Todos los Use Cases
import com.nistra.demy.admins.features.schedules.presentation.state.ScheduleUiState
import com.nistra.demy.admins.features.schedules.presentation.model.ScheduleFormData
import com.nistra.demy.admins.features.schedules.presentation.model.ClassSessionFormData
import com.nistra.demy.admins.features.courses.domain.usecase.GetAllCoursesUseCase // Use Case de otra feature
import com.nistra.demy.admins.features.classrooms.domain.usecase.GetAllClassroomsUseCase // Asume Use Case de otra feature
import com.nistra.demy.admins.features.teachers.domain.usecase.GetTeachersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SchedulesViewModel @Inject constructor(
    // Use Cases de Schedule (CRUD principal)
    private val getAllSchedulesUseCase: GetAllSchedulesUseCase,
    private val createScheduleUseCase: CreateScheduleUseCase,
    private val updateScheduleNameUseCase: UpdateScheduleNameUseCase,
    private val deleteScheduleUseCase: DeleteScheduleUseCase,
    // Use Cases de Sesiones (CRUD anidado)
    private val addClassSessionToScheduleUseCase: AddClassSessionToScheduleUseCase,
    private val removeClassSessionFromScheduleUseCase: RemoveClassSessionFromScheduleUseCase,
    // Use Cases de Data Maestra (Otras features)
    private val getAllCoursesUseCase: GetAllCoursesUseCase,
    private val getAllClassroomsUseCase: GetAllClassroomsUseCase,
    private val getAllTeachersUseCase: GetTeachersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ScheduleUiState())
    val uiState: StateFlow<ScheduleUiState> = _uiState.asStateFlow()

    private var allSchedulesCache: List<Schedule> = emptyList()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                withContext(Dispatchers.IO) {
                    val schedulesResult = getAllSchedulesUseCase()
                    val coursesResult = getAllCoursesUseCase()
                    val teachersResult = getAllTeachersUseCase()
                    val classroomsResult = getAllClassroomsUseCase()

                    schedulesResult.onSuccess { fetchedSchedules ->
                        allSchedulesCache = fetchedSchedules
                        val filteredList = filterSchedules(fetchedSchedules, _uiState.value.searchQuery)
                        // Las actualizaciones de StateFlow son seguras y se reflejarán en el Main Thread.
                        _uiState.update { it.copy(schedules = filteredList) }
                    }.onFailure { e ->
                        // Esto manejará el error 500 del servidor.
                        _uiState.update { it.copy(error = e.message ?: "Error al cargar agendas.") }
                    }

                    coursesResult.onSuccess { courses ->
                        _uiState.update { it.copy(availableCourses = courses) }
                    }

                    teachersResult.onSuccess { teachers ->
                        _uiState.update { it.copy(availableTeachers = teachers) }
                    }

                    classroomsResult.onSuccess { classrooms ->
                        _uiState.update { it.copy(availableClassrooms = classrooms) }
                    }
                }
            } catch (e: Exception) {
                // Captura excepciones inesperadas (ej. problemas de red o Hilt)
                _uiState.update { it.copy(error = "Error de inicialización: ${e.message ?: "Desconocido"}") }
            } finally {
                // 3. Desactiva el indicador de carga.
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    // =========================================================================
    // Handlers de Interacción y Búsqueda
    // =========================================================================

    fun searchSchedules(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        val filteredList = filterSchedules(allSchedulesCache, query)
        _uiState.update { it.copy(schedules = filteredList) }
    }

    private fun filterSchedules(list: List<Schedule>, query: String): List<Schedule> {
        return if (query.isBlank()) {
            list
        } else {
            list.filter { schedule ->
                schedule.name.contains(query, ignoreCase = true)
            }
        }
    }

    fun selectScheduleForEdit(schedule: Schedule) {
        _uiState.update { it.copy(scheduleToEdit = schedule, nameForm = ScheduleFormData(name = schedule.name), error = null) }
    }

    fun clearSelectedSchedule() {
        _uiState.update { it.copy(scheduleToEdit = null, nameForm = ScheduleFormData(), error = null) }
    }

    // =========================================================================
    // Handlers de Forms (Name Form)
    // =========================================================================

    fun onScheduleNameChange(name: String) {
        _uiState.update { it.copy(nameForm = it.nameForm.copy(name = name)) }
    }

    fun saveScheduleName() {
        val name = _uiState.value.nameForm.name.trim()
        val schedule = _uiState.value.scheduleToEdit

        if (name.isBlank()) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val result = if (schedule == null) {
                    createScheduleUseCase(name)
                } else {
                    updateScheduleNameUseCase(schedule.id, name)
                }

                result.onSuccess { updatedSchedule ->
                    // Si es nuevo, selecciona el nuevo Schedule para permitir añadir sesiones
                    updatedSchedule?.let { selectScheduleForEdit(it) }
                    loadData() // Recargar listas
                }.onFailure { e ->
                    _uiState.update { it.copy(isLoading = false, error = e.message ?: "Error al guardar el horario.") }
                }

            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = "Error de conexión o datos.") }
            }
        }
    }

    fun deleteSchedule(schedule: Schedule) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            deleteScheduleUseCase(schedule.id).onSuccess {
                if (_uiState.value.scheduleToEdit?.id == schedule.id) {
                    clearSelectedSchedule()
                }
                loadData()
            }.onFailure { e ->
                _uiState.update { it.copy(isLoading = false, error = e.message ?: "Error al eliminar horario.") }
            }
        }
    }

    // =========================================================================
    // Handlers de Forms (Session Form)
    // =========================================================================

    fun onSessionFormChange(formData: ClassSessionFormData) {
        _uiState.update { it.copy(sessionForm = formData, error = null) }
    }

    fun addClassSession() {
        val scheduleId = _uiState.value.scheduleToEdit?.id ?: return
        val data = _uiState.value.sessionForm

        if (!data.isFormValid) {
            _uiState.update { it.copy(error = "Por favor, completa todos los campos de la sesión.") }
            return
        }

        // CORRECCIÓN: Usar data.teacherId directamente ya que es Long?
        val selectedTeacher = data.teacherId.let { id ->
            _uiState.value.availableTeachers.find { it.id == id }
        }

        // Obtener nombres, utilizando un valor predeterminado si el profesor no se encuentra
        val teacherFirstName = selectedTeacher?.firstName ?: ""
        val teacherLastName = selectedTeacher?.lastName ?: ""

        viewModelScope.launch {
            _uiState.update { it.copy(isSessionFormLoading = true, error = null) }

            addClassSessionToScheduleUseCase(
                scheduleId = scheduleId,
                startTime = data.startTime,
                endTime = data.endTime,
                dayOfWeek = data.selectedDay,
                courseId = data.courseId!!,
                classroomId = data.classroomId!!,
                teacherFirstName = teacherFirstName,
                teacherLastName = teacherLastName
            ).onSuccess { updatedSchedule ->
                // --- INICIO DE LA SOLUCIÓN ---
                if (updatedSchedule != null) {
                    // 1. Actualizar el caché local
                    val newSchedulesCache = allSchedulesCache.map {
                        if (it.id == updatedSchedule.id) updatedSchedule else it
                    }
                    allSchedulesCache = newSchedulesCache

                    // 2. Aplicar el filtro actual a la lista actualizada
                    val filteredList = filterSchedules(newSchedulesCache, _uiState.value.searchQuery)

                    // 3. Actualizar el estado de la UI en una sola operación
                    _uiState.update { state ->
                        state.copy(
                            scheduleToEdit = updatedSchedule, // Refresca el detalle
                            schedules = filteredList,         // Refresca la lista principal
                            sessionForm = ClassSessionFormData(), // Limpia el formulario
                            isSessionFormLoading = false
                        )
                    }
                } else {
                    _uiState.update { it.copy(isSessionFormLoading = false) }
                }
                // NO llames a loadData() aquí
                // --- FIN DE LA SOLUCIÓN ---
            }.onFailure { e ->
                _uiState.update { it.copy(isSessionFormLoading = false, error = e.message ?: "Error al añadir sesión.") }
            }
        }
    }

    // REEMPLAZA ESTA FUNCIÓN COMPLETA
    fun deleteClassSession(session: ClassSession) {
        val scheduleId = _uiState.value.scheduleToEdit?.id ?: return

        viewModelScope.launch {
            _uiState.update { it.copy(isSessionFormLoading = true, error = null) }

            removeClassSessionFromScheduleUseCase(scheduleId, session.id)
                .onSuccess { updatedSchedule ->
                    // --- INICIO DE LA SOLUCIÓN ---
                    if (updatedSchedule != null) {
                        // 1. Actualizar el caché local
                        val newSchedulesCache = allSchedulesCache.map {
                            if (it.id == updatedSchedule.id) updatedSchedule else it
                        }
                        allSchedulesCache = newSchedulesCache

                        // 2. Aplicar el filtro actual a la lista actualizada
                        val filteredList = filterSchedules(newSchedulesCache, _uiState.value.searchQuery)

                        // 3. Actualizar el estado de la UI en una sola operación
                        _uiState.update { state ->
                            state.copy(
                                scheduleToEdit = updatedSchedule, // Refresca el detalle
                                schedules = filteredList,         // Refresca la lista principal
                                isSessionFormLoading = false
                            )
                        }
                    } else {
                        // Si por alguna razón el schedule actualizado es nulo, al menos detén la carga
                        _uiState.update { it.copy(isSessionFormLoading = false) }
                    }
                    // NO llames a loadData() aquí
                    // --- FIN DE LA SOLUCIÓN ---
                }
                .onFailure { e ->
                    _uiState.update { it.copy(isSessionFormLoading = false, error = e.message ?: "Error al eliminar sesión.") }
                }
        }
    }
}
