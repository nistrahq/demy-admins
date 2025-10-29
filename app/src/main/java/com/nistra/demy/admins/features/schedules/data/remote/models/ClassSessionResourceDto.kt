package com.nistra.demy.admins.features.schedules.data.remote.models

import com.squareup.moshi.JsonClass

data class ClassSessionResourceDto(
    val id: Long?,
    val startTime: String?,
    val endTime: String?,
    val dayOfWeek: String?,

    val courseId: Long?,
    val classroomId: Long?,
    val teacherId: Long?,

    val courseName: String?,
    val courseCode: String?,
    val teacherFirstName: String?,
    val teacherLastName: String?,
    val classroomCode: String?,
    val classroomCampus: String?,
)
