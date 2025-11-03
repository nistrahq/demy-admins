package com.nistra.demy.admins.features.billing.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.nistra.demy.admins.core.designsystem.components.feedback.DemySnackbarHost
import com.nistra.demy.admins.core.designsystem.components.feedback.SnackbarEffect
import com.nistra.demy.admins.core.designsystem.components.feedback.rememberDemySnackbarState
import com.nistra.demy.admins.features.billing.presentation.ui.components.BillingAccountsHeader
import com.nistra.demy.admins.features.billing.presentation.ui.components.BillingRegistrationForm
import com.nistra.demy.admins.features.billing.presentation.ui.components.BillingSearchPanel
import com.nistra.demy.admins.features.billing.presentation.viewmodel.RegisterBillingAccountViewModel
import com.nistra.demy.admins.features.teachers.presentation.viewmodel.RegisterTeacherViewModel
import com.nistra.demy.admins.R

@Composable
fun RegisterBillingScreen(
    viewModel: RegisterBillingAccountViewModel = hiltViewModel(),
    @Suppress("UNUSED_PARAMETER") onGoToList: () -> Unit = {}
) {

    // 2. Recolectar los estados del ViewModel de Billing
    val uiState by viewModel.uiState.collectAsState()
    val formData by viewModel.formData.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val snackbarState = rememberDemySnackbarState()

    // 3. El SnackbarEffect se mantiene, solo necesita el UiState de Billing
    SnackbarEffect(
        message = uiState.snackbarMessage,
        snackbarState = snackbarState,
        onMessageShown = viewModel::clearSnackbarMessage
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 4. Usar el Header de Billing con sus strings
            BillingAccountsHeader(
                title = stringResource(R.string.billing_screen_title),
                description = stringResource(R.string.billing_screen_description)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 5. Usar el Formulario de Billing y sus lambdas
                BillingRegistrationForm(
                    formData = formData,
                    onFormChange = viewModel::onFieldChange,
                    onSubmit = viewModel::registerBillingAccount,
                    modifier = Modifier.weight(1f),
                    isLoading = uiState.isLoading
                )

                // 6. Usar el Panel de Búsqueda de Billing y sus lambdas
                BillingSearchPanel(
                    searchQuery = searchQuery,
                    onSearchQueryChange = viewModel::onSearchQueryChange,
                    billingAccounts = uiState.filteredBillingAccounts,
                    modifier = Modifier.weight(1f),
                    isLoading = uiState.isLoadingBillingAccounts
                )
            }
        }

        DemySnackbarHost(
            snackbarState = snackbarState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

// 7. Preview adaptado para la pantalla de Billing
@Preview(showBackground = true, widthDp = 1200, heightDp = 800)
@Composable
fun RegisterBillingScreenPreview() {
    MaterialTheme {
        RegisterBillingScreen()
    }
}
