package com.nistra.demy.admins.features.schedules.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nistra.demy.admins.core.navigation.Destination
import com.nistra.demy.admins.features.schedules.presentation.ui.ScheduleViewerScreen

fun NavGraphBuilder.schedulesViewerGraph(navController: NavHostController) {
    navigation(
        startDestination = Destination.SchedulesViewer.toRoute(),
        route = "scheduling"
    ) {
        composable(Destination.SchedulesViewer.toRoute()) {
            ScheduleViewerScreen()
        }
    }
}
