package com.nistra.demy.admins.features.auth.data.datasource.remote

import com.nistra.demy.admins.core.common.safeApiCall
import com.nistra.demy.admins.features.auth.data.remote.api.AuthApi
import com.nistra.demy.admins.features.auth.data.remote.dto.SignInRequestDto
import com.nistra.demy.admins.features.auth.data.remote.dto.SignInResponseDto
import com.nistra.demy.admins.features.auth.data.remote.dto.SignUpRequestDto
import com.nistra.demy.admins.features.auth.data.remote.dto.SignUpResponseDto
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val api : AuthApi
) : AuthRemoteDataSource {
    override suspend fun signIn(emailAddress: String, password: String): SignInResponseDto {
        val request = SignInRequestDto(emailAddress, password)
        return safeApiCall { api.signIn(request) }
    }

    override suspend fun signUp(
        emailAddress: String,
        password: String,
        roles: List<String>
    ): SignUpResponseDto {
        val request = SignUpRequestDto(emailAddress, password, roles)
        return safeApiCall { api.signUp(request) }
    }
}
