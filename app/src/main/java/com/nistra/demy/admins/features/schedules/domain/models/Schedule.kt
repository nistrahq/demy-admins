package com.nistra.demy.admins.features.schedules.domain.models

import com.squareup.moshi.Json

data class Schedule(
    val id: Long,
    val name: String,
    val classSessions: List<ClassSession>
)
