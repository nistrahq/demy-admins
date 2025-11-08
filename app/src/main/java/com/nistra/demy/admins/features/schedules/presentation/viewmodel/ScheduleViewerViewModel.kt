package com.nistra.demy.admins.features.schedules.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.features.schedules.domain.models.Schedule
import com.nistra.demy.admins.features.schedules.domain.repositories.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewerViewModel @Inject constructor(
    private val repository: ScheduleRepository // Inyecta el repositorio (Real o Fake según la configuración Hilt)
) : ViewModel() {

    private val _allSchedules = MutableStateFlow<List<Schedule>>(emptyList())
    val allSchedules: StateFlow<List<Schedule>> = _allSchedules.asStateFlow()

    private val _selectedSchedule = MutableStateFlow<Schedule?>(null)
    val selectedSchedule: StateFlow<Schedule?> = _selectedSchedule.asStateFlow()

    private val _selectedScheduleName = MutableStateFlow<String>("")
    val selectedScheduleName: StateFlow<String> = _selectedScheduleName.asStateFlow()

    init {
        loadAllSchedules()
    }

    /**
     * Carga todos los schedules disponibles y selecciona el primero por defecto.
     */
    fun loadAllSchedules() {
        viewModelScope.launch {
            val schedules = repository.getAllSchedules()
            _allSchedules.value = schedules

            // Seleccionar el primer schedule por defecto para inicializar la vista
            schedules.firstOrNull()?.let { firstSchedule ->
                selectSchedule(firstSchedule.id, firstSchedule.name)
            }
        }
    }

    /**
     * Selecciona un Schedule por ID, actualizando el estado de la vista.
     */
    fun selectSchedule(id: Long, name: String) {
        val schedule = _allSchedules.value.find { it.id == id }
        _selectedSchedule.value = schedule
        _selectedScheduleName.value = name
    }
}
