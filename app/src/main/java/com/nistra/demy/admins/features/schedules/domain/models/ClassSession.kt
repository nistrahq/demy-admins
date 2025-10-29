package com.nistra.demy.admins.features.schedules.domain.models

data class ClassSession(
    val id: Long,
    val timeRange: TimeRange,
    val dayOfWeek: DayOfWeek,

    val courseId: Long,
    val courseName: String,
    val courseCode: String,

    val teacherId: Long,
    val teacherName: String,

    val classroomId: Long,
    val classroomCode: String,
    val classroomCampus: String,

    val durationMinutes: Int
)
