package com.nistra.demy.admins.features.auth.domain.repository

import com.nistra.demy.admins.features.auth.domain.model.UserSession

interface AuthRepository {
    suspend fun signIn(email: String, password: String): UserSession
}
