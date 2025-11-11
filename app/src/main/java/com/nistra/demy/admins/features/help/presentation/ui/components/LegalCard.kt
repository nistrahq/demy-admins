package com.nistra.demy.admins.features.help.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCard
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCardNoTitle

@Composable
fun LegalCard(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val naranjaContainer = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)

    InfoCard(
        title = "Legal",
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

            // 🔸 Terms & Conditions
            InfoCardNoTitle(
                containerColor = naranjaContainer,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("terms") } // 👈 Navega al tocar la card
            ) {
                Text(
                    text = "Terms & Conditions",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Text(
                    text = "By using our services, you agree to our Terms and Conditions. Please read them before proceeding.",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                )
            }

            // 🔸 Privacy Policy
            InfoCardNoTitle(
                containerColor = naranjaContainer,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("privacy") } // 👈 Navega al tocar la card
            ) {
                Text(
                    text = "Privacy Policy",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Text(
                    text = "By using our services, you agree to our Privacy Policy. We recommend reading it to understand how we handle your data.",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                )
            }
        }
    }
}
