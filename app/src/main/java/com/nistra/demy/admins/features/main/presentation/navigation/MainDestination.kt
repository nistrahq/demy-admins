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

    data object Profile : MainDestination {
        override val route = "main/profile"
        fun toRoute() = route
    }

    data object Settings : MainDestination {
        override val route = "main/settings"
        fun toRoute() = route
    }

    data object Help : MainDestination {
        override val route = "main/help"
        fun toRoute() = route
    }

    data object Billing : MainDestination {
        override val route = "main/billing"
        fun toRoute() = route
    }

    data object Invoices : MainDestination {
        override val route = "main/invoices"
        fun toRoute() = route
    }
}
