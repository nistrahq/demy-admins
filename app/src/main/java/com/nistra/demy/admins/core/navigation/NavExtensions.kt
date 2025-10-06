package com.nistra.demy.admins.core.navigation

import androidx.navigation.NavController

fun NavController.navigateOnce(
    route: String,
    popUpToRoute: String? = null,
    inclusive: Boolean = false
) {
    this.navigate(route) {
        launchSingleTop = true
        restoreState = true
        if (popUpToRoute != null) {
            popUpTo(popUpToRoute) {
                saveState = true
                this.inclusive = inclusive
            }
        }
    }
}

fun NavController.navigateOnce(destination: Destination) {
    navigateOnce(destination.route)
}

fun NavController.popToRoot() {
    this.popBackStack(this.graph.startDestinationId, false)
}

fun NavController.back() {
    popBackStack()
}

fun NavController.replaceCurrent(route: String) {
    popBackStack()
    navigateOnce(route)
}
