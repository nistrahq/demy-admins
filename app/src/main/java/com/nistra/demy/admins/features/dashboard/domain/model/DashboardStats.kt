package com.nistra.demy.admins.features.dashboard.domain.model

/**
 * Domain model representing dashboard statistics.
 *
 * This model contains all the key metrics displayed on the dashboard.
 * It's part of the domain layer and represents pure business data.
 *
 * @property balance Current financial balance.
 * @property currentAcademicPeriod Current academic period number.
 * @property schedules Total number of active schedules.
 * @property totalExpense Total expenses amount.
 * @property totalStudents Total number of students in current period.
 * @property academicPeriodStartDate Start date of the current academic period.
 * @property academicPeriodEndDate End date of the current academic period.
 * @property totalCourses Total number of courses.
 * @property totalClassrooms Total number of classrooms.
 * @property mostOverloadedTeacher Name of the teacher with most workload.
 * @author Salim Ramirez
 */
data class DashboardStats(
    val balance: Int,
    val currentAcademicPeriod: Int,
    val schedules: Int,
    val totalExpense: Int,
    val totalStudents: Int = 0,
    val academicPeriodStartDate: String = "",
    val academicPeriodEndDate: String = "",
    val totalCourses: Int = 0,
    val totalClassrooms: Int = 0,
    val mostOverloadedTeacher: String = "N/A"
)
