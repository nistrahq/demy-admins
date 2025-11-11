package com.nistra.demy.admins.features.help.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCard
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCardNoTitle
import com.nistra.demy.admins.features.help.presentation.ui.components.WeAreHereToHelpCard

@Composable
fun HelpScreen() {
    val moradoContainer = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.4f)
    val naranjaContainer = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)

    // Estados para los modales
    var showTermsDialog by remember { mutableStateOf(false) }
    var showPrivacyDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header principal
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

        // Contenido principal
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // LEFT SECTION
            Column(
                modifier = Modifier
                    .weight(1.4f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 📘 We are here to help (idéntico a AccessibilityTestCard)
                WeAreHereToHelpCard()

                // 🟧 Legal section
                InfoCard(
                    title = "Legal",
                    containerColor = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                        InfoCardNoTitle(
                            containerColor = naranjaContainer,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Terms & Conditions",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                            Text(
                                text = "By using our services, you agree to our Terms and Conditions. Please read them before proceeding.",
                                style = MaterialTheme.typography.bodySmall
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            TextButton(onClick = { showTermsDialog = true }) {
                                Text("Read more")
                            }
                        }

                        InfoCardNoTitle(
                            containerColor = naranjaContainer,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Privacy Policy",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                            Text(
                                text = "By using our services, you agree to our Privacy Policy. We recommend reading it to understand how we handle your data.",
                                style = MaterialTheme.typography.bodySmall
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            TextButton(onClick = { showPrivacyDialog = true }) {
                                Text("Read more")
                            }
                        }
                    }
                }
            }

            // RIGHT SECTION
            Column(
                modifier = Modifier
                    .weight(1.8f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoCard(
                    title = "Help",
                    containerColor = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.fillMaxHeight()
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
                        }
                    }
                }
            }
        }
    }

    // 📜 Terms & Conditions Dialog
    if (showTermsDialog) {
        AlertDialog(
            onDismissRequest = { showTermsDialog = false },
            title = { Text("Terms & Conditions") },
            text = {
                Text(
                    "Here you can place your terms and conditions text.\n\n" +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Suspendisse varius enim in eros elementum tristique. " +
                        "Integer at sem eget metus posuere tincidunt..."
                )
            },
            confirmButton = {
                TextButton(onClick = { showTermsDialog = false }) {
                    Text("Close")
                }
            }
        )
    }

    // 🔒 Privacy Policy Dialog
    if (showPrivacyDialog) {
        AlertDialog(
            onDismissRequest = { showPrivacyDialog = false },
            title = { Text("Privacy Policy") },
            text = {
                Text(
                    "Here you can place your privacy policy text.\n\n" +
                        "This dialog supports scroll automatically for long text. " +
                        "You can expand this with more sections about data usage, storage, etc."
                )
            },
            confirmButton = {
                TextButton(onClick = { showPrivacyDialog = false }) {
                    Text("Close")
                }
            }
        )
    }
}
