package com.nistra.demy.admins.core.navigation.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.nistra.demy.admins.core.ui.components.MainDestination
import com.nistra.demy.admins.core.ui.layout.MainLayout
import com.nistra.demy.admins.core.model.DrawerSection
import com.nistra.demy.admins.core.model.UserUi
import com.nistra.demy.admins.core.navigation.navigateOnce

@Composable
fun MainShell(
    navController: NavHostController,
    title: String,
    content: @Composable () -> Unit) {
    val user = UserUi(
        name = "Profile",
        role = "Administrator"
    )

    val drawerSections = listOf(
        DrawerSection(
            header = "Overview",
            items = listOf(MainDestination.Dashboard)
        ),
        DrawerSection(
            header = "Academy",
            items = listOf(MainDestination.Teachers, MainDestination.Students)
        ),
        DrawerSection(
            header = "Classes",
            items = listOf(MainDestination.Periods, MainDestination.Courses, MainDestination.Classrooms, MainDestination.Schedules)
        ),
        DrawerSection(
            header = "Management",
            items = listOf(MainDestination.Enrollments, MainDestination.Scheduling, MainDestination.Billing, MainDestination.Invoices, MainDestination.Finance)
        ),
        DrawerSection(
            header = "General",
            items = listOf(MainDestination.Settings, MainDestination.Help, MainDestination.LogOut)
        )
    )

    MainLayout(
        title = title,
        appName = "Demy Admins",
        user = user,
        drawerSections = drawerSections,
        selectedDestinationId = navController.currentBackStackEntry?.destination?.route ?: "",
        onDestinationClick = { destination ->
            navController.navigateOnce(destination.id)
        }
    ) {
        content()
    }
}
