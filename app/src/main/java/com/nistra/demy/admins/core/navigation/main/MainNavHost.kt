package com.nistra.demy.admins.core.navigation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nistra.demy.admins.core.navigation.RootDestination
import com.nistra.demy.admins.features.dashboard.presentation.ui.DashboardScreen
import com.nistra.demy.admins.features.teachers.presentation.ui.TeachersScreen

@Composable
fun MainNavHost() {
    val innerNavController = rememberNavController()

    MainShell(
        navController = innerNavController,
        title = currentTitle(innerNavController)
    ) {
        NavHost(
            navController = innerNavController,
            startDestination = RootDestination.Dashboard.route
        ) {
            composable(RootDestination.Dashboard.toRoute()) { DashboardScreen() }
            composable(RootDestination.Teachers.toRoute()) { TeachersScreen() }
        }
    }
}

@Composable
private fun currentTitle(navController: NavHostController): String {
    val currentBackStackEntry by navController.currentBackStackEntryFlow.collectAsState(initial = null)
    val currentRoute = currentBackStackEntry?.destination?.route

    return when (currentRoute) {
        RootDestination.Dashboard.route -> "Dashboard"
        RootDestination.Teachers.route -> "Teachers"
        RootDestination.Students.route -> "Students"
        else -> ""
    }
}
