package com.nistra.demy.admins.features.schedules.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nistra.demy.admins.features.main.presentation.navigation.MainDestination
import com.nistra.demy.admins.features.schedules.presentation.ui.screens.ScheduleViewerScreen
import com.nistra.demy.admins.features.schedules.presentation.ui.screens.SchedulesScreen

fun NavGraphBuilder.schedulesGraph(navController: NavHostController) {
    navigation(
        startDestination = SchedulesDestination.Overview.route,
        route = MainDestination.Schedules.route
    ) {
        composable(SchedulesDestination.Overview.route) {
            SchedulesScreen()
        }

        composable(SchedulesDestination.ScheduleViewer.route) {
            ScheduleViewerScreen()
        }
    }
}
