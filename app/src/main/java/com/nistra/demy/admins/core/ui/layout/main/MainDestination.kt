package com.nistra.demy.admins.core.ui.layout.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Work
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Main destinations for the app's top-level navigation.
 *
 * @param id The unique identifier for the destination.
 * @param label The display label for the destination.
 * @param icon The icon representing the destination.
 * @author Salim Ramirez
 */
sealed class MainDestination(val id: String, val label: String, val icon: ImageVector) {
    data object Dashboard : MainDestination("dashboard", "Dashboard", Icons.Filled.Dashboard)
    data object Teachers : MainDestination("teachers", "Teachers", Icons.Filled.Work)
    data object Students : MainDestination("students", "Students", Icons.Filled.People)
    data object Periods : MainDestination("periods", "Periods", Icons.Filled.CalendarMonth)
    data object Classrooms : MainDestination("classrooms", "Classrooms", Icons.Filled.School)
    data object Courses : MainDestination("courses", "Courses", Icons.AutoMirrored.Filled.MenuBook)
    data object Schedules : MainDestination("schedules", "Schedules", Icons.Filled.Schedule)
    data object Enrollments : MainDestination("enrollments", "Enrollments", Icons.Filled.AppRegistration)
    data object Scheduling : MainDestination("scheduling", "Scheduling", Icons.Filled.CalendarToday)
    data object Billing : MainDestination("billing", "Billing", Icons.Filled.Payment)
    data object Invoices : MainDestination("invoices", "Invoices", Icons.Filled.Description)
    data object Finance : MainDestination("finance", "Finance", Icons.Filled.AccountBalance)
    data object Settings : MainDestination("settings", "Settings", Icons.Filled.Settings)
    data object Help : MainDestination("help", "Help", Icons.AutoMirrored.Filled.Help)
    data object LogOut : MainDestination("logout", "Log Out", Icons.AutoMirrored.Filled.ExitToApp)
}
