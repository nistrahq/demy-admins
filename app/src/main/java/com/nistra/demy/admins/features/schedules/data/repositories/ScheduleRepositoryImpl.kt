package com.nistra.demy.admins.features.schedules.data.repositories

import com.nistra.demy.admins.features.schedules.data.remote.models.AddClassSessionRequest
import com.nistra.demy.admins.features.schedules.data.remote.models.ClassSessionDto
import com.nistra.demy.admins.features.schedules.data.remote.models.CreateScheduleRequest
import com.nistra.demy.admins.features.schedules.data.remote.models.ScheduleDto
import com.nistra.demy.admins.features.schedules.data.remote.models.UpdateClassSessionRequest
import com.nistra.demy.admins.features.schedules.data.remote.services.ScheduleService
import com.nistra.demy.admins.features.schedules.domain.models.DayOfWeek
import com.nistra.demy.admins.features.schedules.domain.models.Schedule
import com.nistra.demy.admins.features.schedules.domain.models.ClassSession
import com.nistra.demy.admins.features.schedules.domain.models.TimeRange
import com.nistra.demy.admins.features.schedules.domain.repositories.ScheduleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


// ====================================================================
// 🚨 LÓGICA DE CÁLCULO (Pertenece a la capa DATA)
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
// IMPLEMENTACIÓN DEL REPOSITORIO (Corregida)
// ====================================================================

class ScheduleRepositoryImpl @Inject constructor(
    private val service: ScheduleService
) : ScheduleRepository {

    private fun ClassSessionDto.toDomain(): ClassSession? {
        // 1. Pre-cálculo de duración
        val startTimeStr = startTime.orEmpty()
        val endTimeStr = endTime.orEmpty()
        val duration = calculateDurationMinutes(startTimeStr, endTimeStr)

        // Validación simplificada: se comprueba que los campos críticos (incluyendo los nuevos) no sean null.
        if (id == null || courseId == null || courseName == null || courseCode == null || classroomId == null || classroomCode == null || classroomCampus == null) {
            return null
        }

        // Construcción del nombre del profesor
        val teacherFullName = "${teacherFirstName.orEmpty()} ${teacherLastName.orEmpty()}"

        return ClassSession(
            id = id,
            timeRange = TimeRange(startTimeStr, endTimeStr),
            // Asegurarse de que el string sea mayúsculas para el enum DayOfWeek
            dayOfWeek = DayOfWeek.valueOf(dayOfWeek?.uppercase() ?: "MONDAY"),
            courseId = courseId,
            classroomId = classroomId,
            teacherId = teacherId ?: 0L,

            courseName = courseName,
            courseCode = courseCode,
            teacherName = teacherFullName,
            classroomCode = classroomCode,
            classroomCampus = classroomCampus,
            durationMinutes = duration // 🚨 CORRECCIÓN CLAVE: Añadir el campo de duración
        )
    }


    private fun ScheduleDto.toDomain(): Schedule? {
        return if (id != null && name != null) {
            Schedule(
                id = id,
                name = name,
                sessions = classSessions?.mapNotNull { it.toDomain() } ?: emptyList()
            )
        } else {
            null
        }
    }

    // 💡 Se implementan todos los métodos aquí, siguiendo el patrón de los repositorios anteriores.
    override suspend fun getAllSchedules(): List<Schedule> = withContext(Dispatchers.IO) {
        val response = service.getAllSchedules()
        return@withContext if (response.isSuccessful) {
            response.body()?.mapNotNull { it.toDomain() } ?: emptyList()
        } else {
            emptyList()
        }
    }

    override suspend fun getScheduleById(id: Long): Schedule? = withContext(Dispatchers.IO) {
        val response = service.getScheduleById(id)
        return@withContext if (response.isSuccessful) response.body()?.toDomain() else null
    }

    override suspend fun getClassSessionsByTeacherId(teacherId: Long): List<ClassSession> = withContext(Dispatchers.IO) {
        val response = service.getClassSessionsByTeacherId(teacherId)
        return@withContext if (response.isSuccessful) response.body()?.mapNotNull { it.toDomain() } ?: emptyList() else emptyList()
    }

    override suspend fun createSchedule(name: String): Schedule? = withContext(Dispatchers.IO) {
        val response = service.createSchedule(CreateScheduleRequest(name))
        return@withContext if (response.isSuccessful) response.body()?.toDomain() else null
    }

    override suspend fun updateScheduleName(id: Long, name: String): Schedule? = withContext(Dispatchers.IO) {
        val response = service.updateScheduleName(id, CreateScheduleRequest(name))
        return@withContext if (response.isSuccessful) response.body()?.toDomain() else null
    }

    override suspend fun deleteSchedule(id: Long) = withContext(Dispatchers.IO) {
        service.deleteSchedule(id)
        Unit
    }

    override suspend fun addClassSessionToSchedule(scheduleId: Long, request: AddClassSessionRequest): Schedule? = withContext(Dispatchers.IO) {
        val response = service.addClassSessionToSchedule(scheduleId, request)
        return@withContext if (response.isSuccessful) response.body()?.toDomain() else null
    }

    override suspend fun removeClassSessionFromSchedule(scheduleId: Long, classSessionId: Long): Schedule? = withContext(Dispatchers.IO) {
        val response = service.removeClassSessionFromSchedule(scheduleId, classSessionId)
        return@withContext if (response.isSuccessful) response.body()?.toDomain() else null
    }

}
