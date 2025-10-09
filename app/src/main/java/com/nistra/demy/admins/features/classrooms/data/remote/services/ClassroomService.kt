package com.nistra.demy.admins.features.classrooms.data.remote.services

import com.nistra.demy.admins.features.classrooms.data.remote.models.ClassroomDto
import com.nistra.demy.admins.features.classrooms.data.remote.models.CreateUpdateClassroomRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ClassroomService {

    @GET("classrooms")
    suspend fun getAllClassrooms(): Response<List<ClassroomDto>>

    @GET("classrooms/{classroomId}")
    suspend fun getClassroomById(@Path("classroomId") classroomId: Long): Response<ClassroomDto>

    @POST("classrooms")
    suspend fun createClassroom(@Body request: CreateUpdateClassroomRequest): Response<ClassroomDto>

    @PUT("classrooms/{classroomId}")
    suspend fun updateClassroom(@Path("classroomId") classroomId: Long, @Body request: CreateUpdateClassroomRequest): Response<ClassroomDto>

    @DELETE("classrooms/{classroomId}")
    suspend fun deleteClassroom(@Path("classroomId") classroomId: Long): Response<Unit>
}
