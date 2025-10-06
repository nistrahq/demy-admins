package com.nistra.demy.admins.features.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.nistra.demy.admins.core.navigation.Destination
import com.nistra.demy.admins.features.auth.presentation.ui.AuthScreen

fun NavGraphBuilder.authGraph(
    onLoggedIn: () -> Unit
) {
    composable(Destination.Auth.route) {
        AuthScreen(onLoggedIn = onLoggedIn)
    }
}
