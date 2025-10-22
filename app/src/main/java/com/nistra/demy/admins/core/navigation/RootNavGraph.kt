package com.nistra.demy.admins.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nistra.demy.admins.core.navigation.auth.AuthNavHost
import com.nistra.demy.admins.core.navigation.main.MainNavHost

@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: String = RootDestination.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(RootDestination.Splash.route) {
            SplashScreen(
                onSessionActive = {
                    navController.navigate(RootDestination.Dashboard.toRoute()) {
                        popUpTo(RootDestination.Splash.route) { inclusive = true }
                    }
                },
                onSessionInactive = {
                    navController.navigate(RootDestination.AuthGraph.toRoute()) {
                        popUpTo(RootDestination.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(RootDestination.AuthGraph.route) {
            AuthNavHost(
                onLoggedIn = {
                navController.navigate(RootDestination.Dashboard.toRoute()) {
                    popUpTo(RootDestination.AuthGraph.route) { inclusive = true }
                }
            })
        }

        composable(RootDestination.Dashboard.route) {
            MainNavHost(rootNavController = navController)
        }
    }
}
