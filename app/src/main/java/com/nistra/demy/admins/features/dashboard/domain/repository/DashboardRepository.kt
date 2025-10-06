package com.nistra.demy.admins.features.dashboard.domain.repository

import com.nistra.demy.admins.features.dashboard.domain.model.DashboardStats

interface DashboardRepository {
    suspend fun fetchStats(): DashboardStats
}
