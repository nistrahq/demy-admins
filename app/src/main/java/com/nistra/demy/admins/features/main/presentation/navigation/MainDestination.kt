package com.nistra.demy.admins.features.main.presentation.navigation

sealed interface MainDestination {
    val route: String

    data object Dashboard : MainDestination {
        override val route = "main/dashboard"
        fun toRoute() = route
    }

    data object Teachers : MainDestination {
        override val route = "main/teachers"
        fun toRoute() = route
    }

    data object Students : MainDestination {
        override val route = "main/students"
        fun toRoute() = route
    }

    data object Courses : MainDestination {
        override val route = "main/courses"
        fun toRoute() = route
    }

}
