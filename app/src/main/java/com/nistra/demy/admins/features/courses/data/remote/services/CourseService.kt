package com.nistra.demy.admins.features.courses.data.remote.services

import com.nistra.demy.admins.features.courses.data.remote.models.CourseDto
import com.nistra.demy.admins.features.courses.data.remote.models.CreateUpdateCourseRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CourseService {

    @GET("courses")
    suspend fun getAllCourses(): Response<List<CourseDto>>

    @GET("courses/{id}")
    suspend fun getCourseById(@Path("id") id: Long): Response<CourseDto>

    @POST("courses")
    suspend fun createCourse(@Body request: CreateUpdateCourseRequest): Response<CourseDto>

    @PUT("courses/{id}")
    suspend fun updateCourse(@Path("id") id: Long, @Body request: CreateUpdateCourseRequest): Response<CourseDto>

    @DELETE("courses/{id}")
    suspend fun deleteCourse(@Path("id") id: Long): Response<Unit>
}
