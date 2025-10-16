package com.nistra.demy.admins.features.auth.domain.usecase

import com.nistra.demy.admins.features.auth.domain.model.UserSession
import com.nistra.demy.admins.features.auth.domain.repository.AuthRepository

class SignInUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): UserSession {
        return repository.signIn(email, password)
    }
}
