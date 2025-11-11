package com.nistra.demy.admins.features.settings.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCard
import com.nistra.demy.admins.core.designsystem.components.cards.ListItemCard
import com.nistra.demy.admins.core.designsystem.theme.extendedColors
import com.nistra.demy.admins.features.settings.domain.model.SettingsUiState
import com.nistra.demy.admins.features.settings.presentation.ui.components.SettingsItem
import com.nistra.demy.admins.features.settings.presentation.viewmodel.SettingsViewModel

@Composable
fun GeneralSettingsCard(
    viewModel: SettingsViewModel,
    uiState: SettingsUiState,
    languageText: String
) {
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
            onClick = { /* Change password */ },
            modifier = Modifier.fillMaxWidth(),
            containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f),
            mainContent = {
                Column {
                    Text("Change password", style = MaterialTheme.typography.titleSmall)
                    Text(
                        "Update your account credentials.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        )

        ListItemCard(
            onClick = { /* Change language */ },
            modifier = Modifier.fillMaxWidth(),
            containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f),
            mainContent = {
                Column {
                    Text("Language", style = MaterialTheme.typography.titleSmall)
                    Text(
                        "Choose your preferred interface language.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            actions = {
                AssistChip(
                    onClick = {},
                    label = { Text(languageText) },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        labelColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            }
        )

        ListItemCard(
            onClick = { /* Report bug */ },
            modifier = Modifier.fillMaxWidth(),
            containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f),
            mainContent = {
                Column {
                    Text("Report a bug", style = MaterialTheme.typography.titleSmall)
                    Text(
                        "Help us improve the app.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        )
    }
}
