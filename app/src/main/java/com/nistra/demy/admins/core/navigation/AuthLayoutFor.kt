package com.nistra.demy.admins.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nistra.demy.admins.core.ui.layout.auth.AuthLayout
import com.nistra.demy.admins.features.auth.navigation.AuthDestination
import com.nistra.demy.admins.features.auth.presentation.ui.AuthScreen

@Composable
fun AuthLayoutFor(onLoggedIn: () -> Unit) {
    val innerNavController = rememberNavController()

    AuthLayout {
        NavHost(
            navController = innerNavController,
            startDestination = AuthDestination.Login.route
        ) {
            composable(AuthDestination.Login.route) {
                AuthScreen(
                    onLoggedIn = onLoggedIn
                )
            }
        }
    }
}
