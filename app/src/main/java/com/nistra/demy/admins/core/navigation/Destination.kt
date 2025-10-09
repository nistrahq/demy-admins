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
sealed interface Destination {
    val route: String

    data object Auth : Destination {
        override val route = "auth"
        fun toRoute() = route
    }

    data object Dashboard : Destination {
        override val route = "dashboard"
        fun toRoute() = route
    }

    data object Teachers : Destination {
        override val route = "teachers"
        fun toRoute() = route
    }

    data object Students : Destination {
        override val route = "students"
        fun toRoute() = route
    }

    data object Courses : Destination {
        override val route = "courses"
        fun toRoute() = route
    }

    data object Classrooms : Destination {
        override val route = "classrooms"
        fun toRoute() = route
    }

    data object Schedules : Destination {
        override val route = "schedules" // Asegurar que implementa route
        fun toRoute() = route
    }

    data object SchedulesViewer : Destination {
        override val route = "schedules_viewer" // Asegurar que implementa route
        fun toRoute() = route
    }


}
