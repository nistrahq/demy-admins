package com.nistra.demy.admins.features.schedules.data.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
data class ScheduleDto(
    val id: Long?,
    val name: String?,
    // El backend llama a la lista 'schedules' (WeeklyScheduleResource.java)
    //@Json(name = "schedules")
    val classSessions: List<ClassSessionDto>?
)
