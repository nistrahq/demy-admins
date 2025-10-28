package com.nistra.demy.admins.features.settings.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCard
import com.nistra.demy.admins.core.designsystem.components.cards.ListItemCard
import com.nistra.demy.admins.core.designsystem.theme.extendedColors
import com.nistra.demy.admins.features.settings.presentation.ui.components.SettingsItem
import com.nistra.demy.admins.features.settings.presentation.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold
        )
        Text(
            text = "Adjust your preferences and application options.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        InfoCard(
            title = "Accessibility Test",
            containerColor = MaterialTheme.extendedColors.info.colorContainer.copy(alpha = 0.4f)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "This is how the text will look in the application.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "This is how the text will look in the application.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "This is how the text will look in the application.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "This is how the text will look in the application.",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "This is how the text will look in the application.",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        InfoCard(
            title = "Theme Configuration",
            containerColor = MaterialTheme.extendedColors.warning.colorContainer.copy(alpha = 0.3f)
        ) {
            SettingsItem(
                title = "Dark Theme",
                description = "Enable or disable dark mode.",
                isChecked = uiState.darkTheme,
                onCheckedChange = viewModel::toggleDarkTheme
            )
            Spacer(modifier = Modifier.height(8.dp))
            SettingsItem(
                title = "High Contrast",
                description = "Improve visibility with higher contrast.",
                isChecked = uiState.highContrast,
                onCheckedChange = viewModel::toggleHighContrast
            )
        }

        InfoCard(
            title = "General",
            containerColor = MaterialTheme.extendedColors.info.colorContainer.copy(alpha = 0.3f)
        ) {
            SettingsItem(
                title = "Notifications",
                description = "Receive updates and alerts.",
                isChecked = uiState.notificationsEnabled,
                onCheckedChange = viewModel::toggleNotifications
            )
            SettingsItem(
                title = "Marketing",
                description = "Allow promotional messages and emails.",
                isChecked = uiState.marketingEnabled,
                onCheckedChange = viewModel::toggleMarketing
            )

            Spacer(modifier = Modifier.height(12.dp))

            ListItemCard(
                onClick = { /* */ },
                modifier = Modifier.fillMaxWidth(),
                containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f),
                mainContent = {
                    Column {
                        Text(
                            text = "Change Password",
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "Update your account credentials.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            )

            ListItemCard(
                onClick = { /**/ },
                modifier = Modifier.fillMaxWidth(),
                containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f),
                mainContent = {
                    Column {
                        Text(
                            text = "Language",
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "Choose your preferred interface language.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            )

            ListItemCard(
                onClick = { /* */ },
                modifier = Modifier.fillMaxWidth(),
                containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f),
                mainContent = {
                    Column {
                        Text(
                            text = "Report a Bug",
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "Help us improve the app.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            )
        }
    }
}
