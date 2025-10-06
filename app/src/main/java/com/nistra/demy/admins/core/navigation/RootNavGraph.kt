package com.nistra.demy.admins.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.nistra.demy.admins.features.auth.navigation.authGraph
import com.nistra.demy.admins.features.dashboard.presentation.navigation.dashboardGraph
import com.nistra.demy.admins.features.teachers.navigation.teachersGraph

/**
 * Root navigation graph that includes all other navigation graphs.
 *
 * It sets up the navigation host and defines the start destination.
 * @param navController The NavHostController to manage navigation.
 * @param startDestination The starting destination route, defaulting to the Auth screen.
 * @author Salim Ramirez
 */
@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: String = Destination.Auth.toRoute()
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        authGraph(
            onLoggedIn = {
                navController.navigateOnce(
                    Destination.Dashboard.toRoute(),
                    popUpToRoute = Destination.Auth.toRoute(),
                    inclusive = true
                )
            }
        )
        dashboardGraph(navController)
        teachersGraph(navController)
    }
}
