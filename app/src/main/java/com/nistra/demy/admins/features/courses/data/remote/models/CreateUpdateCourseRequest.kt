package com.nistra.demy.admins.features.courses.data.remote.models

//Verificar si se debe crear por separado
// DTO de solicitud, mapeado de CreateCourseResource.java y UpdateCourseResource.java
data class CreateUpdateCourseRequest(
    val name: String,
    val code: String,
    val description: String
)
