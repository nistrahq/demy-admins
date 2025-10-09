package com.nistra.demy.admins.features.schedules.domain.models

// Mapea el WeeklySchedule.java
data class Schedule(
    val id: Long,
    val name: String,
    val sessions: List<ClassSession>
)
