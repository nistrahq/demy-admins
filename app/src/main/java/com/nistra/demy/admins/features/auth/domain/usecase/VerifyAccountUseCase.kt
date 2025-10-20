package com.nistra.demy.admins.features.auth.domain.usecase

import com.nistra.demy.admins.features.auth.domain.repository.AuthRepository
import javax.inject.Inject

class VerifyAccountUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, code: String): Result<String> {
        return runCatching { repository.verifyAccount(email, code) }
    }
}
