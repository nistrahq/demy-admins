package com.nistra.demy.admins.features.dashboard.data.repository

import com.nistra.demy.admins.features.dashboard.domain.model.DashboardStats
import com.nistra.demy.admins.features.dashboard.domain.repository.DashboardRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor() : DashboardRepository {
    override suspend fun fetchStats(): DashboardStats {
        delay(1000)
        return DashboardStats(
            balance = 1500,
            currentAcademicPeriod = 3,
            schedules = 25,
            totalExpense = 5000
        )
    }
}
