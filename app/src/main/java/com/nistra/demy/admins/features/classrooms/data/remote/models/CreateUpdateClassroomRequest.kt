package com.nistra.demy.admins.features.classrooms.data.remote.models

data class CreateUpdateClassroomRequest(
    val code: String,
    val capacity: Int,
    val campus: String
)
