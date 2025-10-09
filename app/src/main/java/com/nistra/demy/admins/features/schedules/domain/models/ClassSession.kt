package com.nistra.demy.admins.features.schedules.domain.models

data class ClassSession(
    val id: Long,
    val timeRange: TimeRange,
    val dayOfWeek: DayOfWeek,
    val courseId: Long,
    val classroomId: Long,
    val teacherId: Long,

    // Campos enriquecidos para la UI (similares a ScheduleResource.java)
    val courseName: String,
    val courseCode: String,
    val teacherName: String, //usar algo del shared?? full name
    val classroomCode: String,
    val classroomCampus: String,
    val durationMinutes: Int   // 🚨🚨🚨 Campo agregado para cálculo de UI
)
