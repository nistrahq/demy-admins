package com.nistra.demy.admins.features.profile.data.repository

import com.nistra.demy.admins.features.profile.domain.model.AcademyInfo
import com.nistra.demy.admins.features.profile.domain.repository.AcademyRepository
import jakarta.inject.Inject
import kotlinx.coroutines.delay

class AcademyRepositoryImpl @Inject constructor() : AcademyRepository {

    override suspend fun fetchAcademyInfo(): AcademyInfo {
        delay(800)

        return AcademyInfo(
            name = "Nistra Academy",
            address = "Av. Los Ingenieros 202",
            ruc = "20567891234",
            email = "info@nistra.com",
            phone = "+51 900111222",
            description = "Formando líderes tecnológicos"
        )
    }
}
