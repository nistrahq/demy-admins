package com.nistra.demy.admins.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nistra.demy.admins.core.ui.layout.main.MainDestination
import com.nistra.demy.admins.core.ui.layout.main.MainLayout
import com.nistra.demy.admins.core.ui.layout.model.DrawerSection
import com.nistra.demy.admins.core.ui.layout.model.UserUi

/**
 * The main app shell that sets up navigation and the overall layout.
 *
 * It uses a NavController to manage navigation between different screens.
 * Depending on the current route, it either shows the authentication flow
 * or the main app layout with a drawer and top bar.
 *
 * @Composable indicates that this function can be used as a UI component in Jetpack Compose.
 * @author Nistra Team
 */
@Composable
fun AppShell() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: ""

    // Detect if we're in the auth flow
    val isAuthRoute = currentRoute.startsWith(Destination.Auth.route)

    // Example user (replace with real data later)
    val user = UserUi(
        name = "Profile",
        role = "Administrator"
    )

    // Drawer sections
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

    if (isAuthRoute) {
        // Auth or standalone screens → no layout base
        RootNavGraph(navController)
    } else {
        // Main app flow → use layout base
        MainLayout(
            title = when (currentRoute) {
                Destination.Dashboard.route -> "Dashboard"
                Destination.Teachers.route -> "Teachers"
                Destination.Students.route -> "Students"
                Destination.Courses.route -> "Courses"
                Destination.Classrooms.route -> "Classrooms"
                Destination.Schedules.route -> "Schedules"
                Destination.SchedulesViewer.route -> "Schedules Viewer"
                else -> ""
            },
            appName = "Demy Admins",
            user = user,
            drawerSections = drawerSections,
            selectedDestinationId = currentRoute,
            onDestinationClick = { destination ->
                navController.navigateOnce(destination.id)
            }
        ) {
            RootNavGraph(navController)
        }
    }
}
