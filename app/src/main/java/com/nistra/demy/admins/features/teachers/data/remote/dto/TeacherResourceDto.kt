package com.nistra.demy.admins.features.teachers.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TeacherResourceDto(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    @field:Json(name = "phoneNumber") val phone: String,
    val academyId: String
)
