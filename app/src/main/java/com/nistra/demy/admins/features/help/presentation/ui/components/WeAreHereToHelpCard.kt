package com.nistra.demy.admins.features.help.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nistra.demy.admins.R

@Composable
fun WeAreHereToHelpCard(
    email: String,
    phone: String,
    schedule: String,
    modifier: Modifier = Modifier
) {
    val poppinsBold = FontFamily(Font(R.font.poppins_bold))
    val poppinsRegular = FontFamily(Font(R.font.poppins_regular))

    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 170.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 1f)
        ),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // 🔹 Título principal
            Text(
                text = "We are here to help you",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = poppinsBold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 20.sp
                )
            )

            // 🔹 Subtítulo
            Text(
                text = "Have questions or need help? We’re here to assist you.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = poppinsRegular,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 15.sp
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            // 🔹 Contact section
            Column {
                Text(
                    text = "Contact",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontFamily = poppinsRegular,
                        fontSize = 13.sp
                    )
                )
                Text(
                    text = "demy@contact.com",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontFamily = poppinsBold,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "WhatsApp",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontFamily = poppinsRegular,
                            fontSize = 13.sp
                        )
                    )
                    Text(
                        text = "+51 999 999 999",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontFamily = poppinsBold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "Schedule",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontFamily = poppinsRegular,
                            fontSize = 13.sp
                        )
                    )
                    Text(
                        text = "Mon–Fri, 9am–6pm",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontFamily = poppinsRegular,
                            fontSize = 14.sp
                        )
                    )
                }
            }
        }
    }
}
