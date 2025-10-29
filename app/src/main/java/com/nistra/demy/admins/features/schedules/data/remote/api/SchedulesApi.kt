package com.nistra.demy.admins.features.schedules.data.remote.api

import com.nistra.demy.admins.features.schedules.data.remote.models.*
import retrofit2.http.*

interface SchedulesApi {

    @GET("weekly-schedules")
    suspend fun getAllSchedules(): List<ScheduleResourceDto>

    @GET("weekly-schedules/{scheduleId}")
    suspend fun getScheduleById(@Path("scheduleId") scheduleId: Long): ScheduleResourceDto

    @GET("weekly-schedules/by-teacher/{teacherId}")
    suspend fun getClassSessionsByTeacherId(@Path("teacherId") teacherId: Long): List<ClassSessionResourceDto>

    @POST("weekly-schedules")
    suspend fun createSchedule(@Body request: CreateScheduleRequestDto): ScheduleResourceDto

    @PUT("weekly-schedules/{scheduleId}")
    suspend fun updateScheduleName(@Path("scheduleId") scheduleId: Long, @Body request: CreateScheduleRequestDto): ScheduleResourceDto

    @DELETE("weekly-schedules/{scheduleId}")
    suspend fun deleteSchedule(@Path("scheduleId") scheduleId: Long): Unit

    @POST("weekly-schedules/{scheduleId}/schedules")
    suspend fun addClassSessionToSchedule(@Path("scheduleId") scheduleId: Long, @Body request: AddClassSessionRequestDto): ScheduleResourceDto

    @DELETE("weekly-schedules/{scheduleId}/schedules/{classSessionId}")
    suspend fun removeClassSessionFromSchedule(@Path("scheduleId") scheduleId: Long, @Path("classSessionId") classSessionId: Long): ScheduleResourceDto
}
