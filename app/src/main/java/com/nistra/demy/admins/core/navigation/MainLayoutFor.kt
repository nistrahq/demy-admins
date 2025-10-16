package com.nistra.demy.admins.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.nistra.demy.admins.core.ui.layout.main.MainDestination
import com.nistra.demy.admins.core.ui.layout.main.MainLayout
import com.nistra.demy.admins.core.ui.layout.model.DrawerSection
import com.nistra.demy.admins.core.ui.layout.model.UserUi

@Composable
fun MainLayoutFor(
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
