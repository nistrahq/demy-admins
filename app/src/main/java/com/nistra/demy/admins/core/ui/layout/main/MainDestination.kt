package com.nistra.demy.admins.core.ui.layout.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.School
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
    data object Teachers : MainDestination("teachers", "Teachers", Icons.Filled.School)
    data object Students : MainDestination("students", "Students", Icons.Filled.Group)
}
