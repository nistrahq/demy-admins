package com.nistra.demy.admins.features.auth.navigation

sealed interface AuthDestination {
    val route: String

    data object Login : AuthDestination {
        override val route = "auth/login"
        fun toRoute() = route
    }

    data object Signup : AuthDestination {
        override val route = "auth/signup"
        fun toRoute() = route
    }

    data object Activate : AuthDestination {
        override val route = "auth/activate/{email}"
        fun toRoute(email: String) = "auth/activate/$email"
    }
}
