package com.nistra.demy.admins.features.auth.data.remote.api

import com.nistra.demy.admins.features.auth.data.remote.dto.SignInRequestDto
import com.nistra.demy.admins.features.auth.data.remote.dto.SignInResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("authentication/sign-in")
    suspend fun signIn(@Body request: SignInRequestDto): SignInResponseDto
}
