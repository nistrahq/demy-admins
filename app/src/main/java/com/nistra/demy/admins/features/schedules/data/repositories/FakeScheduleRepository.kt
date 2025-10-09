package com.nistra.demy.admins.features.schedules.data.repositories

import com.nistra.demy.admins.features.schedules.domain.models.ClassSession
import com.nistra.demy.admins.features.schedules.domain.models.DayOfWeek
import com.nistra.demy.admins.features.schedules.domain.models.Schedule
import com.nistra.demy.admins.features.schedules.domain.models.TimeRange
import com.nistra.demy.admins.features.schedules.domain.repositories.ScheduleRepository
import com.nistra.demy.admins.features.schedules.data.remote.models.AddClassSessionRequest
import com.nistra.demy.admins.features.schedules.data.remote.models.UpdateClassSessionRequest
import javax.inject.Inject


// ====================================================================
// 🚨 LÓGICA DE CÁLCULO (Pertenece a la capa DATA/MOCK)
// ====================================================================

private fun timeStringToMinutes(timeStr: String): Int {
    return try {
        val parts = timeStr.split(":")
        val hours = parts.getOrElse(0) { "0" }.toInt()
        val minutes = parts.getOrElse(1) { "0" }.toInt()
        hours * 60 + minutes
    } catch (e: Exception) { 0 }
}

private fun calculateDurationMinutes(startTime: String, endTime: String): Int {
    return timeStringToMinutes(endTime) - timeStringToMinutes(startTime)
}

// ====================================================================
// 🚨 DATOS FALSOS ESTRUCTURADOS (LISTA DE SCHEDULES)
// ====================================================================

val mockScheduleList = listOf(
    Schedule(
        id = 1L,
        name = "Veranito 25-1",
        sessions = listOf(
            ClassSession(
                id = 101L,
                timeRange = TimeRange("08:00", "10:00"),
                dayOfWeek = DayOfWeek.MONDAY,
                courseId = 1L, classroomId = 1L, teacherId = 50L,
                courseName = "Arquitectura de Software", courseCode = "AS201",
                teacherName = "Dr. Ana Gómez", classroomCode = "A301",
                classroomCampus = "San Isidro",
                durationMinutes = calculateDurationMinutes("08:00", "10:00") // 🚨 Cálculo aplicado
            ),
            ClassSession(
                id = 102L,
                timeRange = TimeRange("10:30", "12:30"),
                dayOfWeek = DayOfWeek.MONDAY,
                courseId = 2L, classroomId = 2L, teacherId = 51L,
                courseName = "Bases de Datos Avanzadas", courseCode = "DB402",
                teacherName = "Ing. Juan Pérez", classroomCode = "L502",
                classroomCampus = "San Isidro",
                durationMinutes = calculateDurationMinutes("10:30", "12:30") // 🚨 Cálculo aplicado
            ),
            ClassSession(
                id = 201L,
                timeRange = TimeRange("11:00", "14:00"),
                dayOfWeek = DayOfWeek.TUESDAY,
                courseId = 3L, classroomId = 3L, teacherId = 52L,
                courseName = "Redes y Telecomunicaciones", courseCode = "RT305",
                teacherName = "Mgt. Laura Ruiz", classroomCode = "C203",
                classroomCampus = "Monterrico",
                durationMinutes = calculateDurationMinutes("11:00", "14:00") // 🚨 Cálculo aplicado
            )
        )
    ),
    Schedule(
        id = 2L,
        name = "Horario Mock: Verano",
        sessions = listOf(
            ClassSession(
                id = 301L,
                timeRange = TimeRange("17:00", "19:00"),
                dayOfWeek = DayOfWeek.THURSDAY,
                courseId = 4L, classroomId = 4L, teacherId = 53L,
                courseName = "Seguridad Informática", courseCode = "SI401",
                teacherName = "Lic. Carlos Vega", classroomCode = "S405",
                classroomCampus = "San Isidro",
                durationMinutes = calculateDurationMinutes("17:00", "19:00") // 🚨 Cálculo aplicado
            )
        )
    ),
    Schedule(
        id = 3L,
        name = "Horario Intercambio",
        sessions = emptyList() // Horario sin sesiones para probar el estado vacío
    )
)

// ====================================================================
// REPOSITORIO FALSO (Devuelve la estructura de arriba)
// ====================================================================

class FakeScheduleRepository @Inject constructor() : ScheduleRepository {

    override suspend fun getAllSchedules(): List<Schedule> {
        return mockScheduleList
    }

    override suspend fun getScheduleById(id: Long): Schedule? {
        return mockScheduleList.find { it.id == id }
    }

    // --- Implementaciones Dummy para Hilt ---
    override suspend fun getClassSessionsByTeacherId(teacherId: Long): List<ClassSession> = emptyList()
    override suspend fun createSchedule(name: String): Schedule? = null
    override suspend fun updateScheduleName(id: Long, name: String): Schedule? = null
    override suspend fun deleteSchedule(id: Long) {}
    override suspend fun addClassSessionToSchedule(scheduleId: Long, request: AddClassSessionRequest): Schedule? = null
    override suspend fun removeClassSessionFromSchedule(scheduleId: Long, classSessionId: Long): Schedule? = null
}
