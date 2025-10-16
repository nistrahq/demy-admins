package com.nistra.demy.admins.features.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nistra.demy.admins.core.navigation.AuthLayoutFor
import com.nistra.demy.admins.core.navigation.Destination

fun NavGraphBuilder.authGraph(navController: NavHostController, onLoggedIn: () -> Unit) {
    navigation(
        startDestination = AuthDestination.Login.toRoute(),
        route = Destination.AuthGraph.toRoute()
    ) {
        composable(Destination.AuthGraph.route) {
            AuthLayoutFor(onLoggedIn)
        }
    }
}
