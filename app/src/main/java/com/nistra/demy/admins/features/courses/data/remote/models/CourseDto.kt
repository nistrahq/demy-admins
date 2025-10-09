package com.nistra.demy.admins.features.courses.data.remote.models

// DTO de respuesta, mapeado de CourseResource.java del backend
data class CourseDto(
    val id: Long?,
    val name: String?,
    val code: String?,
    val description: String?
)
