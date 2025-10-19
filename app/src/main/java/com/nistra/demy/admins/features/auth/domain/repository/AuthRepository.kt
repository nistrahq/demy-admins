package com.nistra.demy.admins.features.auth.domain.repository

import com.nistra.demy.admins.features.auth.domain.model.UserSession

interface AuthRepository {
    suspend fun signIn(emailAddress: String, password: String): UserSession
}
