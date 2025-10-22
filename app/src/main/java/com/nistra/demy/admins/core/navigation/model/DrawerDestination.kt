package com.nistra.demy.admins.core.navigation.model

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
import com.nistra.demy.admins.features.main.presentation.navigation.MainDestination

/**
 * Main destinations for the app's top-level navigation.
 *
 * @param id The unique identifier for the destination.
 * @param label The display label for the destination.
 * @param icon The icon representing the destination.
 * @author Salim Ramirez
 */
sealed class DrawerDestination(val id: String, val label: String, val icon: ImageVector) {
    data object Dashboard : DrawerDestination(MainDestination.Dashboard.route, "Dashboard", Icons.Filled.Dashboard)
    data object Teachers : DrawerDestination(MainDestination.Teachers.route, "Teachers", Icons.Filled.Work)
    data object Students : DrawerDestination(MainDestination.Students.route, "Students", Icons.Filled.People)
    data object Periods : DrawerDestination("periods", "Periods", Icons.Filled.CalendarMonth)
    data object Classrooms : DrawerDestination("classrooms", "Classrooms", Icons.Filled.School)
    data object Courses : DrawerDestination("courses", "Courses", Icons.AutoMirrored.Filled.MenuBook)
    data object Schedules : DrawerDestination("schedules", "Schedules", Icons.Filled.Schedule)
    data object Enrollments : DrawerDestination("enrollments", "Enrollments", Icons.Filled.AppRegistration)
    data object Scheduling : DrawerDestination("scheduling", "Scheduling", Icons.Filled.CalendarToday)
    data object Billing : DrawerDestination("billing", "Billing", Icons.Filled.Payment)
    data object Invoices : DrawerDestination("invoices", "Invoices", Icons.Filled.Description)
    data object Finance : DrawerDestination("finance", "Finance", Icons.Filled.AccountBalance)
    data object Settings : DrawerDestination("settings", "Settings", Icons.Filled.Settings)
    data object Help : DrawerDestination("help", "Help", Icons.AutoMirrored.Filled.Help)
    data object LogOut : DrawerDestination("logout", "Log Out", Icons.AutoMirrored.Filled.ExitToApp)
}
