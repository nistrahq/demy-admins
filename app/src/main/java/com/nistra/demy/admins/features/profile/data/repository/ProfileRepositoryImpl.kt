package com.nistra.demy.admins.features.profile.data.repository

import com.nistra.demy.admins.features.profile.domain.model.GeneralInfo
import com.nistra.demy.admins.features.profile.domain.repository.ProfileRepository
import jakarta.inject.Inject
import kotlinx.coroutines.delay

class ProfileRepositoryImpl @Inject constructor() : ProfileRepository {
    override suspend fun fetchProfileInfo(): GeneralInfo {
        delay(800)

        return GeneralInfo(
            name = "Andrea",
            lastName = "Aponte",
            dni = "12345678",
            email = "andrea@nistra.com",
            phone = "+51 987654321"
        )
    }
}
