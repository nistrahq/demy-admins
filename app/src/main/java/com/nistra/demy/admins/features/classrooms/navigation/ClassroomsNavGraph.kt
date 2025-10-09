package com.nistra.demy.admins.features.classrooms.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nistra.demy.admins.core.navigation.Destination
import com.nistra.demy.admins.features.classrooms.presentation.ui.ClassroomsScreen

fun NavGraphBuilder.classroomsGraph(navController: NavHostController) {
    navigation(
        startDestination = Destination.Classrooms.toRoute(),
        route = "classrooms_graph"
    ) {
        composable(Destination.Classrooms.toRoute()) {
            ClassroomsScreen()
        }
    }
}
