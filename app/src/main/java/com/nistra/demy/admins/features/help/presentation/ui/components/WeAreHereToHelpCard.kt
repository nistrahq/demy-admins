package com.nistra.demy.admins.features.help.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCardNoTitle

@Composable
fun WeAreHereToHelpCard(
    modifier: Modifier = Modifier
) {
    InfoCardNoTitle(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) {
        Text(
            text = "We are here to help you",
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.SemiBold
            )
        )

        Text(
            text = "Have questions or need help? We’re here to assist you.",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Contact",
            style = MaterialTheme.typography.labelSmall.copy(
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
        Text(
            text = "demy@contact.com",
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Medium
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "WhatsApp",
            style = MaterialTheme.typography.labelSmall.copy(
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
        Text(
            text = "+51 999 999 999",
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Medium
            )
        )
    }
}
