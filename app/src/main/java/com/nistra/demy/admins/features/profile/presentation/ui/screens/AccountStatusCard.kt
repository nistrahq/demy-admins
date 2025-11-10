package com.nistra.demy.admins.features.profile.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCard
import com.nistra.demy.admins.features.profile.presentation.ui.components.InlineValue

@Composable
fun AccountStatusCard(accountStatus: String, modifier: Modifier = Modifier) {
    InfoCard(
        title = "Account Status",
        modifier = modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.4f)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            InlineValue(label = "Role", value = "ADMINISTRATOR")
            InlineValue(label = "Status", value = accountStatus)
            InlineValue(label = "Verification", value = "VERIFIED")
            InlineValue(label = "Academies", value = "1")
        }
    }
}
