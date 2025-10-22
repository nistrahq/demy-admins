package com.nistra.demy.admins.core.designsystem.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.nistra.demy.admins.core.storage.SessionPreferences
import com.nistra.demy.admins.core.navigation.model.DrawerDestination
import com.nistra.demy.admins.core.designsystem.model.DrawerSection
import com.nistra.demy.admins.core.designsystem.model.UserUi
import com.nistra.demy.admins.core.navigation.RootDestination
import com.nistra.demy.admins.core.navigation.navigateOnce
import com.nistra.demy.admins.features.main.presentation.navigation.currentParentRouteAsState
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

    val currentRoute = navController.currentParentRouteAsState()

    val drawerSections = listOf(
        DrawerSection(
            header = "Overview",
            items = listOf(DrawerDestination.Dashboard)
        ),
        DrawerSection(
            header = "Academy",
            items = listOf(DrawerDestination.Teachers, DrawerDestination.Students)
        ),
        DrawerSection(
            header = "Classes",
            items = listOf(DrawerDestination.Periods, DrawerDestination.Courses, DrawerDestination.Classrooms, DrawerDestination.Schedules)
        ),
        DrawerSection(
            header = "Management",
            items = listOf(DrawerDestination.Enrollments, DrawerDestination.Scheduling, DrawerDestination.Billing, DrawerDestination.Invoices, DrawerDestination.Finance)
        ),
        DrawerSection(
            header = "General",
            items = listOf(DrawerDestination.Settings, DrawerDestination.Help, DrawerDestination.LogOut)
        )
    )

    val implementedRoutes = setOf(
        DrawerDestination.Dashboard.id,
        DrawerDestination.Teachers.id,
        DrawerDestination.Students.id
    )

    MainLayout(
        title = title,
        appName = "Demy Admins",
        user = user,
        drawerSections = drawerSections,
        selectedDestinationId = currentRoute,
        onDestinationClick = { destination ->
            when {
                destination == DrawerDestination.LogOut -> {
                    scope.launch {
                        sessionPreferences.clearSession()
                        rootNavController.navigate(RootDestination.AuthGraph.toRoute()) {
                            popUpTo(RootDestination.MainGraph.toRoute()) { inclusive = true }
                        }
                    }
                }
                destination.id in implementedRoutes -> {
                    navController.navigateOnce(destination.id)
                }
                else -> {
                    // TODO: Implementar navegación para ${destination.label}
                    android.util.Log.w("MainShell", "Navigation to ${destination.label} (${destination.id}) not yet implemented")
                }
            }
        }
    ) {
        content()
    }
}
