package com.nistra.demy.admins.core.designsystem.config

import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.designsystem.model.DrawerSection
import com.nistra.demy.admins.core.navigation.model.DrawerDestination

/**
 * Configuration object for the main drawer sections.
 *
 * This centralizes the drawer structure configuration, making it easier to maintain
 * and update the navigation structure.
 *
 * @author Salim Ramirez
 */
object DrawerConfig {
    /**
     * Returns the list of drawer sections for the main navigation.
     */
    fun getSections(): List<DrawerSection> = listOf(
        DrawerSection(
            headerResId = R.string.drawer_section_overview,
            items = listOf(DrawerDestination.Dashboard)
        ),
        DrawerSection(
            headerResId = R.string.drawer_section_academy,
            items = listOf(DrawerDestination.Teachers, DrawerDestination.Students)
        ),
        DrawerSection(
            headerResId = R.string.drawer_section_classes,
            items = listOf(
                DrawerDestination.Periods,
                DrawerDestination.Courses,
                DrawerDestination.Classrooms,
                DrawerDestination.Schedules
            )
        ),
        DrawerSection(
            headerResId = R.string.drawer_section_management,
            items = listOf(
                DrawerDestination.Enrollments,
                DrawerDestination.Scheduling,
                DrawerDestination.Billing,
                DrawerDestination.Invoices,
                DrawerDestination.Finance
            )
        ),
        DrawerSection(
            headerResId = R.string.drawer_section_general,
            items = listOf(
                DrawerDestination.Settings,
                DrawerDestination.Help,
                DrawerDestination.LogOut
            )
        )
    )

    /**
     * Set of implemented routes that have actual screen implementations.
     */
    val implementedRoutes: Set<String> = setOf(
        DrawerDestination.Dashboard.id,
        DrawerDestination.Teachers.id,
        DrawerDestination.Students.id,
        DrawerDestination.Settings.id
    )
}

