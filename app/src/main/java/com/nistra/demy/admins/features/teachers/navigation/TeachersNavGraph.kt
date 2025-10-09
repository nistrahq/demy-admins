package com.nistra.demy.admins.features.teachers.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nistra.demy.admins.core.navigation.Destination
import com.nistra.demy.admins.features.teachers.presentation.ui.TeachersScreen

fun NavGraphBuilder.teachersGraph(navController: NavHostController) {
    navigation(
        startDestination = Destination.Teachers.toRoute(),
        route = "teachers_graph"
    ) {
        composable(Destination.Teachers.toRoute()) {
            TeachersScreen()
        }
    }
}
