package com.nistra.demy.admins.features.help.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCard
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCardNoTitle

@Composable
fun HelpCard(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val moradoContainer = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.4f)

    InfoCard(
        title = "Help",
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

            InfoCardNoTitle(containerColor = moradoContainer) {
                Text(
                    text = "Report a bug",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    text = "If you’ve found a bug or something isn’t working as expected, please report it. Your feedback helps us improve and provide a better experience for everyone.",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(onClick = { navController.navigate("report_bug") }) {
                    Text("Go to report form")
                }
            }

            InfoCardNoTitle(containerColor = moradoContainer) {
                Text(
                    text = "Contribute",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    text = "Want to contribute to the project? Whether it’s ideas, code, or suggestions, your input is welcome and helps us grow.",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(onClick = { navController.navigate("contribute") }) {
                    Text("Get started")
                }
            }
        }
    }
}
