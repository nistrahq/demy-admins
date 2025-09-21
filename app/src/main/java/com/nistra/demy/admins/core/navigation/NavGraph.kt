package com.nistra.demy.admins.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RootNavGraph(navController: NavHostController, startDestination: String = Routes.AUTH) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // No arguments
        composable(Routes.AUTH) {
            // AuthScreen(
            //     onLoggedIn = { navController.navigate(Routes.HOME) }
            // )
        }
    }
}