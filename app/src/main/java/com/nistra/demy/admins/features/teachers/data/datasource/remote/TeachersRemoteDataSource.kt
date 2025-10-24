package com.nistra.demy.admins.features.teachers.data.datasource.remote

import com.nistra.demy.admins.features.teachers.data.remote.dto.TeacherResourceDto

interface TeachersRemoteDataSource {
    suspend fun fetchTeachers(): List<TeacherResourceDto>

    suspend fun addTeacher(
        firstName: String,
        lastName: String,
        email: String,
        countryCode: String,
        phone: String
    ): TeacherResourceDto
}
