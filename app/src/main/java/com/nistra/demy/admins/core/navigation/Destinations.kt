package com.nistra.demy.admins.core.navigation

sealed class Destination(val route: String) {
    data object Auth : Destination(Routes.AUTH)
    data object Home : Destination(Routes.HOME)
    data object Profile : Destination(Routes.PROFILE)
}