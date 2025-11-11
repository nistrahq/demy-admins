package com.nistra.demy.admins.features.help.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCard
import com.nistra.demy.admins.features.help.presentation.ui.components.HelpCard
import com.nistra.demy.admins.features.help.presentation.ui.components.WeAreHereToHelpCard
import com.nistra.demy.admins.features.help.presentation.ui.components.LegalCard

@Composable
fun HelpScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 🟢 Encabezado
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

        // 🟦 Contenido principal dividido en dos columnas
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min), // 👈 iguala la altura entre columnas
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Columna izquierda: WeAreHereToHelp + Legal
            Column(
                modifier = Modifier
                    .weight(1.4f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                WeAreHereToHelpCard(
                    email = "demy@contact.com",
                    phone = "+51 999 999 999",
                    schedule = "Mon–Fri, 9am–6pm"
                )

                LegalCard(navController)
            }

            // Columna derecha: Help
            Column(
                modifier = Modifier
                    .weight(1.8f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                HelpCard()
            }
        }
    }
}
