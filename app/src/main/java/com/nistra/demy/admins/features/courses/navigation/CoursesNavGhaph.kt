package com.nistra.demy.admins.features.courses.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nistra.demy.admins.core.navigation.Destination
import com.nistra.demy.admins.features.courses.presentation.ui.CoursesScreen

fun NavGraphBuilder.coursesGraph(navController: NavHostController) {
    navigation(
        startDestination = Destination.Courses.toRoute(),
        route = "courses_graph"
    ) {
        composable(Destination.Courses.toRoute()) {
            CoursesScreen()
        }
        // Aquí irían otras rutas específicas de Courses (e.g., /courses/create)
    }
}
