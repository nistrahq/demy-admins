package com.nistra.demy.admins.features.profile.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCard
import com.nistra.demy.admins.core.designsystem.components.layout.ImageHeaderSection
import com.nistra.demy.admins.features.profile.presentation.state.ProfileUiState
import com.nistra.demy.admins.features.profile.presentation.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is ProfileUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is ProfileUiState.Error -> {
            val message = (uiState as ProfileUiState.Error).message
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center
                )
            }
        }

        is ProfileUiState.Success -> {
            val stats = (uiState as ProfileUiState.Success).stats
            val general = stats.generalInfo
            val academy = stats.academyInfo

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
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
                    Column(
                        modifier = Modifier
                            .weight(0.9f)
                            .wrapContentHeight(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        InfoCard(
                            title = "Account Status",
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 260.dp),
                            containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.4f)
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                InlineValue(label = "Role", value = "ADMINISTRATOR")
                                InlineValue(label = "Status", value = stats.accountStatus)
                                InlineValue(label = "Verification", value = "VERIFIED")
                                InlineValue(label = "Academies", value = "1")
                            }
                        }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(145.dp),
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
                                    style = MaterialTheme.typography.headlineSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 24.sp,
                                        color = MaterialTheme.colorScheme.onSecondaryContainer
                                    ),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                    InfoCard(
                        title = "Profile",
                        modifier = Modifier
                            .weight(1.1f)
                            .wrapContentHeight(),
                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow
                                ),
                                shape = MaterialTheme.shapes.medium
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(12.dp)
                                        .fillMaxWidth(),
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.teachers_management_header_photo),
                                            contentDescription = "User photo",
                                            modifier = Modifier
                                                .size(90.dp)
                                                .clip(RoundedCornerShape(12.dp)),
                                            contentScale = ContentScale.Crop
                                        )

                                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                            Text(
                                                "General",
                                                style = MaterialTheme.typography.titleMedium.copy(
                                                    fontWeight = FontWeight.SemiBold,
                                                    fontSize = 16.sp
                                                )
                                            )
                                            InlineValue(label = "Name", value = "${general.name} ${general.lastName}")
                                            InlineValue(label = "Phone", value = general.phone)
                                            InlineValue(label = "Email", value = general.email)
                                            InlineValue(label = "DNI", value = general.dni)
                                        }
                                    }

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        Button(
                                            onClick = { /**/ },
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                                            ),
                                            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 6.dp)
                                        ) {
                                            Text("Edit", fontSize = 11.sp)
                                        }
                                    }
                                }
                            }

                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow
                                ),
                                shape = MaterialTheme.shapes.medium
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(12.dp)
                                        .fillMaxWidth(),
                                    verticalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Text(
                                        "Academy Information",
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 15.sp
                                        )
                                    )
                                    InlineValue(label = "Name", value = academy.name)
                                    InlineValue(label = "Description", value = academy.description)
                                    InlineValue(label = "Address", value = academy.address)
                                    InlineValue(label = "Phone", value = academy.phone)
                                    InlineValue(label = "Email", value = academy.email)
                                    InlineValue(label = "RUC", value = academy.ruc)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun InlineValue(label: String, value: String) {
    Text(
        text = "$label: $value",
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    )
}
