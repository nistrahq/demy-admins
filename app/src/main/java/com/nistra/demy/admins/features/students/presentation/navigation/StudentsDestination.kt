package com.nistra.demy.admins.features.students.presentation.navigation

sealed interface StudentsDestination {
    val route: String

    data object Register : StudentsDestination {
        override val route = "students/register"
        fun toRoute() = route
    }
}

