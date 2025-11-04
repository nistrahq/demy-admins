package com.nistra.demy.admins.features.help.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCard

@Composable
fun HelpScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {

        // TOP DESCRIPTION CARD
        InfoCard(
            title = "Do you need help?",
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ) {
            Text(
                text = "Do you need help with something? We’re here to support you—feel free to reach out anytime.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // MAIN CONTENT AREA
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // LEFT SIDE
            Column(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // CONTACT CARD
                InfoCard(
                    title = "We are here to help you",
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = "Have questions or need help? We’re here to assist you.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimary
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Contact",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Text(
                            text = "demy@contact.com",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "WhatsApp",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Text(
                            text = "+51 999 999 999",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }

                // LEGAL SECTION
                InfoCard(
                    title = "Legal",
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        InfoCard(
                            title = "Terms & Conditions",
                            containerColor = MaterialTheme.colorScheme.tertiaryContainer
                        ) {
                            Text(
                                text = "By using our services, you agree to our Terms and Conditions. Please read them before proceeding.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        }

                        InfoCard(
                            title = "Privacy Policy",
                            containerColor = MaterialTheme.colorScheme.tertiaryContainer
                        ) {
                            Text(
                                text = "By using our services, you agree to our Privacy Policy. We recommend reading it to understand how we handle your data.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        }
                    }
                }
            }

            // RIGHT SIDE
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                InfoCard(
                    title = "Help",
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        InfoCard(
                            title = "Report a bug",
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        ) {
                            Text(
                                text = "If you’ve found a bug or something isn’t working as expected, please report it. Your feedback helps us improve and provide a better experience for everyone.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }

                        InfoCard(
                            title = "Contribute",
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        ) {
                            Text(
                                text = "Want to contribute to the project? Whether it’s ideas, code, or suggestions, your input is welcome and helps us grow.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }
                }
            }
        }
    }
}
