package com.nistra.demy.admins.features.profile.domain.repository

import com.nistra.demy.admins.features.profile.data.remote.ProfileStats

interface ProfileRepository {
    suspend fun fetchProfileStats(): ProfileStats
}
