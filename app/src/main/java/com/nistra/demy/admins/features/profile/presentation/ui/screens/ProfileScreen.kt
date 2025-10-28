package com.nistra.demy.admins.features.profile.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCard
import com.nistra.demy.admins.core.designsystem.components.text.LabeledValue
import com.nistra.demy.admins.core.designsystem.components.layout.ImageHeaderSection

//just to try
@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ImageHeaderSection(
            title = "See and edit your profile information",
            description = "Manage your personal data and academy details",
            backgroundImage = R.drawable.teachers_management_header_photo,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // IZQUIERDA
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoCard(
                    title = "Account Status",
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.4f)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        LabeledValue(label = "Role", value = "ADMINISTRATOR")
                        LabeledValue(label = "Status", value = "ACTIVE")
                        LabeledValue(label = "Verification", value = "VERIFIED")
                        LabeledValue(label = "Academies", value = "1")
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
                    ),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "HAVE A\nGREAT DAY",
                            style = MaterialTheme.typography.displayMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            ),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }
            }

            // DERECHA
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoCard(
                    title = "Profile",
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        //perfil
                        Image(
                            painter = painterResource(id = R.drawable.teachers_management_header_photo),
                            contentDescription = "User photo",
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(16.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("General", fontWeight = FontWeight.SemiBold)
                            LabeledValue(label = "Name", value = "Andrea")
                            LabeledValue(label = "Last name", value = "Paredes")
                            LabeledValue(label = "Phone", value = "999999999")
                            LabeledValue(label = "Email", value = "andrea.paredes@demy.edu.pe")
                            LabeledValue(label = "DNI", value = "12312312")
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { /**/ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    ) {
                        Text("Edit")
                    }
                }

                InfoCard(
                    title = "Academy Information",
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("General", fontWeight = FontWeight.SemiBold)
                        LabeledValue(label = "Name", value = "Lorem Academy")
                        LabeledValue(label = "Address", value = "Av. Example 123")
                        LabeledValue(label = "Phone", value = "987654321")
                        LabeledValue(label = "Email", value = "contact@lorem.edu.pe")
                        LabeledValue(label = "RUC", value = "12312312312")
                    }
                }
            }
        }
    }
}
