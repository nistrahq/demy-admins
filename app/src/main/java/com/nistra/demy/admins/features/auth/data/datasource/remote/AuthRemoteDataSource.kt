package com.nistra.demy.admins.features.auth.data.datasource.remote

import com.nistra.demy.admins.core.common.Resource
import com.nistra.demy.admins.features.auth.data.remote.dto.SignInRequestDto
import com.nistra.demy.admins.features.auth.data.remote.dto.SignInResponseDto

interface AuthRemoteDataSource {
    suspend fun signIn(request: SignInRequestDto): Resource<SignInResponseDto>
}
