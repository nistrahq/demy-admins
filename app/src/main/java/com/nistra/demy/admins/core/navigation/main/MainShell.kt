package com.nistra.demy.admins.core.navigation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.nistra.demy.admins.core.data.local.SessionPreferences
import com.nistra.demy.admins.core.ui.components.MainDestination
import com.nistra.demy.admins.core.ui.layout.MainLayout
import com.nistra.demy.admins.core.model.DrawerSection
import com.nistra.demy.admins.core.model.UserUi
import com.nistra.demy.admins.core.navigation.RootDestination
import com.nistra.demy.admins.core.navigation.navigateOnce
import kotlinx.coroutines.launch

@Composable
fun MainShell(
    navController: NavHostController,
    rootNavController: NavHostController,
    title: String,
    sessionPreferences: SessionPreferences,
    content: @Composable () -> Unit
) {
    val user = UserUi(
        name = "Profile",
        role = "Administrator"
    )

    val scope = rememberCoroutineScope()

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
            if (destination == MainDestination.LogOut) {
                scope.launch {
                    sessionPreferences.clearSession()
                    rootNavController.navigate(RootDestination.AuthGraph.toRoute()) {
                        popUpTo(RootDestination.Dashboard.toRoute()) { inclusive = true }
                    }
                }
            } else {
                navController.navigateOnce(destination.id)
            }
        }
    ) {
        content()
    }
}
