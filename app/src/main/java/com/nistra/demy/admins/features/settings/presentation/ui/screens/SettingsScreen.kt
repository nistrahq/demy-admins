package com.nistra.demy.admins.features.settings.presentation.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
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
    val isDarkTheme = isSystemInDarkTheme()
    val themeText = if (isDarkTheme) "Dark" else "Light"
    val locale = LocalConfiguration.current.locales[0].language
    val languageText = when (locale) {
        "es" -> "Español"
        "en" -> "English"
        else -> "System"
    }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val isWideScreen = maxWidth >= 900.dp

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            //Header
            Text(
                text = "Settings",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.SemiBold
            )

            //Intro card
            InfoCard(
                title = "Adjust your preferences",
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            ) {
                Text(
                    text = "Settings are a set of preferences that you can customize to enhance your experience. " +
                        "You can modify settings such as theme, notifications, privacy, and more.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (isWideScreen) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        AccessibilityTestCard()
                        ThemeConfigurationCard(
                            isDarkTheme = isDarkTheme,
                            themeText = themeText,
                            uiState = uiState,
                            viewModel = viewModel
                        )
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        GeneralSettingsCard(
                            uiState = uiState,
                            viewModel = viewModel,
                            languageText = languageText
                        )
                    }
                }
            } else {
                AccessibilityTestCard()
                ThemeConfigurationCard(
                    isDarkTheme = isDarkTheme,
                    themeText = themeText,
                    uiState = uiState,
                    viewModel = viewModel
                )
                GeneralSettingsCard(
                    uiState = uiState,
                    viewModel = viewModel,
                    languageText = languageText
                )
            }
        }
    }
}
@Composable
private fun AccessibilityTestCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E3A8A)),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 20.dp,
                    top = 16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Accessibility Test",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Adjust the accessibility options to reflect changes in this block.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
            Text(
                text = "This is how the text will look in the application.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
            Text(
                text = "This is how the text will look in the application.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
            Text(
                text = "This is how the text will look in the application.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
            Text(
                text = "This is how the text will look in the application.",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
            Text(
                text = "This is how the text will look in the application.",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
        }
    }
}
@Composable
private fun ThemeConfigurationCard(
    isDarkTheme: Boolean,
    themeText: String,
    uiState: com.nistra.demy.admins.features.settings.presentation.viewmodel.SettingsUiState,
    viewModel: SettingsViewModel
) {
    InfoCard(
        title = "Theme configuration",
        containerColor = MaterialTheme.extendedColors.warning.colorContainer.copy(alpha = 0.3f)
    ) {
        SettingsItem(
            title = "Dark theme",
            description = "Current: $themeText mode (follows system theme).",
            isChecked = isDarkTheme,
            onCheckedChange = {}
        )
        Spacer(modifier = Modifier.height(8.dp))
        SettingsItem(
            title = "High contrast",
            description = "Improve visibility with higher contrast.",
            isChecked = uiState.highContrast,
            onCheckedChange = viewModel::toggleHighContrast
        )
    }
}

@Composable
private fun GeneralSettingsCard(
    uiState: com.nistra.demy.admins.features.settings.presentation.viewmodel.SettingsUiState,
    viewModel: SettingsViewModel,
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
