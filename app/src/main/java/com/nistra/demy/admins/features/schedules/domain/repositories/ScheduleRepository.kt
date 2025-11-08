package com.nistra.demy.admins.features.schedules.domain.repositories

import com.nistra.demy.admins.features.schedules.domain.models.Schedule
import com.nistra.demy.admins.features.schedules.domain.models.ClassSession
import com.nistra.demy.admins.features.schedules.data.remote.models.AddClassSessionRequest
import com.nistra.demy.admins.features.schedules.data.remote.models.UpdateClassSessionRequest


interface ScheduleRepository {
    // Queries
    suspend fun getAllSchedules(): List<Schedule>
    suspend fun getScheduleById(id: Long): Schedule?
    suspend fun getClassSessionsByTeacherId(teacherId: Long): List<ClassSession>

    // Commands - Schedule
    suspend fun createSchedule(name: String): Schedule?
    suspend fun updateScheduleName(id: Long, name: String): Schedule?
    suspend fun deleteSchedule(id: Long)

    // Commands - ClassSession (Nested)
    suspend fun addClassSessionToSchedule(scheduleId: Long, request: AddClassSessionRequest): Schedule?
    suspend fun removeClassSessionFromSchedule(scheduleId: Long, classSessionId: Long): Schedule?
}
