package com.nistra.demy.admins.features.billing.presentation.navigation

sealed interface BillingDestination {
    val route: String

    data object Billing : BillingDestination {
        override val route = "billing"
        fun toRoute() = route
    }
}

