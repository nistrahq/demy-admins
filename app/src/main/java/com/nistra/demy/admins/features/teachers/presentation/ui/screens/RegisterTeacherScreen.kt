package com.nistra.demy.admins.features.teachers.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.nistra.demy.admins.features.teachers.presentation.viewmodel.RegisterTeacherViewModel

@Preview(showBackground = true)
@Composable
fun RegisterTeacherScreen(
    viewModel: RegisterTeacherViewModel = hiltViewModel(),
    onGoToList: () -> Unit = {}
) {
    val isLoading by viewModel.isLoading.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Teachers Register Screen",
                style = MaterialTheme.typography.headlineMedium
            )

            Button(onClick = {
                viewModel.registerTeacher()
                onGoToList()
            }) {
                Text("Register Teacher")
            }

            Button(onClick = onGoToList) {
                Text("Go to Teachers List")
            }
        }
    }
}
