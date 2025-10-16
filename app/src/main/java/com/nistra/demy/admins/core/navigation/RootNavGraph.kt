package com.nistra.demy.admins.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: String = Destination.AuthGraph.toRoute()
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Destination.AuthGraph.route) {
            AuthLayoutFor(onLoggedIn = {
                navController.navigate(Destination.Dashboard.route) {
                    popUpTo(Destination.AuthGraph.route) { inclusive = true }
                }
            })
        }

        composable(Destination.Dashboard.route) {
            MainGraph()
        }
    }
}
