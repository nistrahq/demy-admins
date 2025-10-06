package com.nistra.demy.admins.features.dashboard.domain.model

data class DashboardStats(
    val balance: Int,
    val currentAcademicPeriod: Int,
    val schedules: Int,
    val totalExpense: Int
)
