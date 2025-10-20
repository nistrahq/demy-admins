package com.nistra.demy.admins.features.auth.data.remote.api

import com.nistra.demy.admins.core.common.MessageResponseDto
import com.nistra.demy.admins.features.auth.data.remote.dto.SignInRequestDto
import com.nistra.demy.admins.features.auth.data.remote.dto.SignInResponseDto
import com.nistra.demy.admins.features.auth.data.remote.dto.SignUpRequestDto
import com.nistra.demy.admins.features.auth.data.remote.dto.SignUpResponseDto
import com.nistra.demy.admins.features.auth.data.remote.dto.VerifyAccountRequestDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("authentication/sign-in")
    suspend fun signIn(@Body request: SignInRequestDto): SignInResponseDto

    @POST("authentication/sign-up")
    suspend fun signUp(@Body request: SignUpRequestDto): SignUpResponseDto

    @POST("authentication/verify")
    suspend fun verifyAccount(@Body request: VerifyAccountRequestDto): MessageResponseDto
}
