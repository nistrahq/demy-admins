package com.nistra.demy.admins.features.schedules.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nistra.demy.admins.core.navigation.Destination
import com.nistra.demy.admins.features.schedules.presentation.ui.ScheduleViewerScreen
import com.nistra.demy.admins.features.schedules.presentation.ui.SchedulesScreen

fun NavGraphBuilder.schedulesGraph(navController: NavHostController) {
    navigation(
        startDestination = Destination.Schedules.toRoute(),
        route = "schedules_graph"
    ) {
        composable(Destination.Schedules.toRoute()) {
            SchedulesScreen()
        }
    }
}
