package com.nistra.demy.admins.features.schedules.data.remote.models

/**
 * Modelo de solicitud para crear un nuevo Schedule (anteriormente WeeklySchedule).
 * Mapea CreateWeeklyScheduleResource.java, que solo contiene el nombre.
 */
data class CreateScheduleRequest(
    val name: String
)
