package com.nistra.demy.admins.features.schedules.data.mapper

import com.nistra.demy.admins.features.schedules.data.remote.models.ClassSessionResourceDto
import com.nistra.demy.admins.features.schedules.data.remote.models.CreateScheduleRequestDto
import com.nistra.demy.admins.features.schedules.data.remote.models.ScheduleResourceDto
import com.nistra.demy.admins.features.schedules.domain.models.ClassSession
import com.nistra.demy.admins.features.schedules.domain.models.DayOfWeek
import com.nistra.demy.admins.features.schedules.domain.models.Schedule
import com.nistra.demy.admins.features.schedules.domain.models.TimeRange

private fun timeStringToMinutes(timeStr: String): Int {
    return try {
        val parts = timeStr.split(":")
        val hours = parts.getOrElse(0) { "0" }.toInt()
        val minutes = parts.getOrElse(1) { "0" }.toInt()
        hours * 60 + minutes
    } catch (e: Exception) { 0 }
}

private fun calculateDurationMinutes(startTime: String, endTime: String): Int {
    val duration = timeStringToMinutes(endTime) - timeStringToMinutes(startTime)
    // Asegura que la duración sea positiva
    return if (duration < 0) 0 else duration
}

fun ClassSessionResourceDto.toDomain(): ClassSession? {
    val startTimeStr = startTime.orEmpty()
    val endTimeStr = endTime.orEmpty()
    val duration = calculateDurationMinutes(startTimeStr, endTimeStr)

    if (id == null || courseId == null || courseName.isNullOrBlank() || courseCode.isNullOrBlank() || classroomId == null || classroomCode.isNullOrBlank() || classroomCampus.isNullOrBlank() || dayOfWeek.isNullOrBlank()) {
        return null
    }

    val teacherFullName = "${teacherFirstName.orEmpty()} ${teacherLastName.orEmpty()}"

    return ClassSession(
        id = id,
        timeRange = TimeRange(startTimeStr, endTimeStr),
        dayOfWeek = try {
            DayOfWeek.valueOf(dayOfWeek.uppercase())
        } catch (e: IllegalArgumentException) {
            DayOfWeek.MONDAY
        },
        courseId = courseId,
        classroomId = classroomId,
        teacherId = teacherId ?: 0L,

        courseName = courseName,
        courseCode = courseCode,
        teacherName = teacherFullName,
        classroomCode = classroomCode,
        classroomCampus = classroomCampus,
        durationMinutes = duration
    )
}

fun ScheduleResourceDto.toDomain(): Schedule? {
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

fun String.toCreateScheduleRequestDto(): CreateScheduleRequestDto {
    return CreateScheduleRequestDto(name = this)
}
