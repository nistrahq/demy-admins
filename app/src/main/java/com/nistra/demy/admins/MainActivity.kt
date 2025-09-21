package com.nistra.demy.admins

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.nistra.demy.admins.core.design.theme.DemyTheme
import com.nistra.demy.admins.core.navigation.RootNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Demy()
        }
    }
}

@Composable
private fun Demy() {
    val navController = rememberNavController()
    DemyTheme {
        RootNavGraph(navController = navController)
    }
}