package com.nistra.demy.admins.features.auth.data.repository

import com.nistra.demy.admins.core.common.Resource
import com.nistra.demy.admins.features.auth.data.datasource.remote.AuthRemoteDataSource
import com.nistra.demy.admins.features.auth.data.mapper.toDomain
import com.nistra.demy.admins.features.auth.data.remote.dto.SignInRequestDto
import com.nistra.demy.admins.features.auth.domain.model.UserSession
import com.nistra.demy.admins.features.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource
) : AuthRepository {
    override suspend fun signIn(email: String, password: String): UserSession {
        return when (val result = remoteDataSource.signIn(SignInRequestDto(email, password))) {
            is Resource.Success -> result.data.toDomain()
            is Resource.Error -> throw Exception(result.message)
        }
    }
}
