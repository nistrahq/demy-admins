package com.nistra.demy.admins.features.dashboard.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nistra.demy.admins.core.navigation.Destination
import com.nistra.demy.admins.features.dashboard.presentation.ui.DashboardScreen

fun NavGraphBuilder.dashboardGraph(navController: NavHostController) {
    navigation(
        startDestination = Destination.Dashboard.route,
        route = "dashboard_graph"
    ) {
        composable(Destination.Dashboard.route) {
            DashboardScreen()
        }
    }
}
