package com.nistra.demy.admins.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppShell() {
    val navController = rememberNavController()
    RootNavGraph(navController = navController)
}
