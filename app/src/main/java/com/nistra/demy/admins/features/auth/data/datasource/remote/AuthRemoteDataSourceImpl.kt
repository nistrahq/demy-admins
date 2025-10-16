package com.nistra.demy.admins.features.auth.data.datasource.remote

import com.nistra.demy.admins.core.common.Resource
import com.nistra.demy.admins.core.common.safeApiCall
import com.nistra.demy.admins.features.auth.data.remote.api.AuthApi
import com.nistra.demy.admins.features.auth.data.remote.dto.SignInRequestDto
import com.nistra.demy.admins.features.auth.data.remote.dto.SignInResponseDto
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val api : AuthApi
) : AuthRemoteDataSource {
    override suspend fun signIn(request: SignInRequestDto): Resource<SignInResponseDto> {
        return safeApiCall { api.signIn(request) }
    }
}
