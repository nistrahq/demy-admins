package com.nistra.demy.admins.features.help.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCard
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCardNoTitle

@Composable
fun HelpScreen() {
    val moradoContainer = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
    val naranjaContainer = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        InfoCard(
            title = "Do you need help?",
            modifier = Modifier.fillMaxWidth(),
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ) {
            Text(
                text = "Do you need help with something? We’re here to support you—feel free to reach out anytime.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            //LEFT SECTION
            Column(
                modifier = Modifier.weight(1.4f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoCardNoTitle(containerColor = Color(0xFF1E3A8A)) {
                    Text(
                        text = "We are here to help you",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )

                    Text(
                        text = "Have questions or need help? We’re here to assist you.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Contact",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White
                    )
                    Text(
                        text = "demy@contact.com",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "WhatsApp",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White
                    )
                    Text(
                        text = "+51 999 999 999",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.White
                    )
                }

                InfoCard(
                    title = "Legal",
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                        InfoCardNoTitle(
                            containerColor = naranjaContainer
                        ) {
                            Text(
                                text = "Terms & Conditions",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            Text(
                                text = "By using our services, you agree to our Terms and Conditions. Please read them before proceeding.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }

                        InfoCardNoTitle(
                            containerColor = naranjaContainer
                        ) {
                            Text(
                                text = "Privacy Policy",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            Text(
                                text = "By using our services, you agree to our Privacy Policy. We recommend reading it to understand how we handle your data.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }
                }
            }

            //RIGHT SECTION
            Column(
                modifier = Modifier.weight(1.8f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoCard(
                    title = "Help",
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                        InfoCardNoTitle(
                            containerColor = moradoContainer
                        ) {
                            Text(
                                text = "Report a bug",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "If you’ve found a bug or something isn’t working as expected, please report it. Your feedback helps us improve and provide a better experience for everyone.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }

                        InfoCardNoTitle(
                            containerColor = moradoContainer
                        ) {
                            Text(
                                text = "Contribute",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "Want to contribute to the project? Whether it’s ideas, code, or suggestions, your input is welcome and helps us grow.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }
            }
        }
    }
}
