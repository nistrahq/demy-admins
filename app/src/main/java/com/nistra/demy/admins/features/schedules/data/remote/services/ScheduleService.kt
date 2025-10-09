package com.nistra.demy.admins.features.schedules.data.remote.services

import com.nistra.demy.admins.features.schedules.data.remote.models.*
import retrofit2.Response
import retrofit2.http.*

interface ScheduleService {

    @GET("weekly-schedules")
    suspend fun getAllSchedules(): Response<List<ScheduleDto>>

    @GET("weekly-schedules/{scheduleId}")
    suspend fun getScheduleById(@Path("scheduleId") scheduleId: Long): Response<ScheduleDto>

    @GET("weekly-schedules/by-teacher/{teacherId}")
    suspend fun getClassSessionsByTeacherId(@Path("teacherId") teacherId: Long): Response<List<ClassSessionDto>>

    @POST("weekly-schedules")
    suspend fun createSchedule(@Body request: CreateScheduleRequest): Response<ScheduleDto>

    @PUT("weekly-schedules/{scheduleId}")
    suspend fun updateScheduleName(@Path("scheduleId") scheduleId: Long, @Body request: CreateScheduleRequest): Response<ScheduleDto>

    @DELETE("weekly-schedules/{scheduleId}")
    suspend fun deleteSchedule(@Path("scheduleId") scheduleId: Long): Response<Unit>

    // 💡 Rutas de ClassSession (Nota: Usa 'schedules' en el path del backend)
    @POST("weekly-schedules/{scheduleId}/schedules")
    suspend fun addClassSessionToSchedule(@Path("scheduleId") scheduleId: Long, @Body request: AddClassSessionRequest): Response<ScheduleDto>

    @DELETE("weekly-schedules/{scheduleId}/schedules/{classSessionId}")
    suspend fun removeClassSessionFromSchedule(@Path("scheduleId") scheduleId: Long, @Path("classSessionId") classSessionId: Long): Response<ScheduleDto>
}
