package com.nistra.demy.admins.core.navigation

/**
 * Defines all navigation destinations used in the application.
 *
 * Each Destination represents a distinct screen or flow in the app.
 * This sealed interface provides a type-safe way to navigate without
 * relying on string literals.
 *
 * To add a new destination, simply create a new object that implements
 * the Destination interface and define its route.
 *
 * @param route The unique route string for the destination.
 * @see androidx.navigation.NavController
 * @see androidx.navigation.NavGraph
 * @see androidx.navigation.compose.NavHost
 * @author Salim Ramirez
 */
sealed interface RootDestination {
    val route: String

    data object AuthGraph : RootDestination {
        override val route = "auth-graph"
        fun toRoute() = route
    }

    data object Dashboard : RootDestination {
        override val route = "dashboard"
        fun toRoute() = route
    }

    data object Teachers : RootDestination {
        override val route = "teachers"
        fun toRoute() = route
    }

    data object Students : RootDestination {
        override val route = "students"
        fun toRoute() = route
    }
}
