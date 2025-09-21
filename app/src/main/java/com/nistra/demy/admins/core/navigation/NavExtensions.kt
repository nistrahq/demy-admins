package com.nistra.demy.admins.core.navigation

import androidx.navigation.NavController

fun NavController.navigateOnce(route: String) {
    this.navigate(route) {
        launchSingleTop = true
    }
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