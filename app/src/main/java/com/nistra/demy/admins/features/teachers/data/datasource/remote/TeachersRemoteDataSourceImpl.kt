package com.nistra.demy.admins.features.teachers.data.datasource.remote

import com.nistra.demy.admins.core.common.safeApiCall
import com.nistra.demy.admins.features.teachers.data.remote.api.TeachersApi
import com.nistra.demy.admins.features.teachers.data.remote.dto.CreateTeacherRequestDto
import com.nistra.demy.admins.features.teachers.data.remote.dto.TeacherResourceDto
import javax.inject.Inject

class TeachersRemoteDataSourceImpl @Inject constructor(
    private val api : TeachersApi
) : TeachersRemoteDataSource {
    override suspend fun fetchTeachers(): List<TeacherResourceDto> {
        return safeApiCall(endpoint = "teachers") { api.getAllTeachers() }
    }

    override suspend fun addTeacher(
        firstName: String,
        lastName: String,
        email: String,
        countryCode: String,
        phone: String
    ): TeacherResourceDto {
        val request = CreateTeacherRequestDto(
            firstName = firstName,
            lastName = lastName,
            email = email,
            countryCode = countryCode,
            phone = phone
        )
        return safeApiCall(endpoint = "teachers") { api.createTeacher(request) }
    }
}
