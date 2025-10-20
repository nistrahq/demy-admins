package com.nistra.demy.admins.features.auth.domain.repository

import com.nistra.demy.admins.features.auth.domain.model.UserSession

interface AuthRepository {
    suspend fun signIn(emailAddress: String, password: String): UserSession

    suspend fun signUp(
        emailAddress: String,
        password: String,
        roles: List<String>
    ): UserSession

    suspend fun verifyAccount(email: String, code: String): String
}
