package com.nistra.demy.admins.features.settings.presentation.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCard
import com.nistra.demy.admins.core.designsystem.theme.extendedColors
import com.nistra.demy.admins.features.settings.domain.model.SettingsUiState
import com.nistra.demy.admins.features.settings.presentation.ui.components.SettingsItem
import com.nistra.demy.admins.features.settings.presentation.viewmodel.SettingsViewModel

@Composable
fun ThemeConfigurationCard(
    viewModel: SettingsViewModel,
    uiState: SettingsUiState,
    themeText: String,
    isDarkTheme: Boolean
) {
    InfoCard(
        title = "Theme configuration",
        containerColor = MaterialTheme.extendedColors.warning.colorContainer.copy(alpha = 0.3f)
    ) {
        SettingsItem(
            title = "Dark theme",
            description = "Current: $themeText mode (follows system theme).",
            isChecked = isDarkTheme,
            onCheckedChange = viewModel::toggleDarkTheme
        )

        Spacer(modifier = androidx.compose.ui.Modifier.height(8.dp))

        SettingsItem(
            title = "High contrast",
            description = "Improve visibility with higher contrast.",
            isChecked = uiState.highContrast,
            onCheckedChange = viewModel::toggleHighContrast
        )
    }
}
