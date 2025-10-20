package com.nistra.demy.admins.features.auth.data.datasource.remote

import com.nistra.demy.admins.core.common.MessageResponseDto
import com.nistra.demy.admins.features.auth.data.remote.dto.SignInResponseDto
import com.nistra.demy.admins.features.auth.data.remote.dto.SignUpResponseDto

interface AuthRemoteDataSource {
    suspend fun signIn(emailAddress: String, password: String): SignInResponseDto

    suspend fun signUp(emailAddress: String, password: String, roles: List<String>): SignUpResponseDto

    suspend fun verifyAccount(email: String, code: String): MessageResponseDto
}
