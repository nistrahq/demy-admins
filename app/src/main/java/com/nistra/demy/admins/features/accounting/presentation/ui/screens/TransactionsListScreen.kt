package com.nistra.demy.admins.features.accounting.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.nistra.demy.admins.R
import com.nistra.demy.admins.features.accounting.presentation.ui.components.AccountingHeader
import com.nistra.demy.admins.features.accounting.presentation.ui.components.DeleteConfirmationDialog
import com.nistra.demy.admins.features.accounting.presentation.ui.components.EditTransactionDialog
import com.nistra.demy.admins.features.accounting.presentation.ui.components.TransactionSearchSection
import com.nistra.demy.admins.features.accounting.presentation.ui.components.TransactionsTable
import com.nistra.demy.admins.features.accounting.presentation.viewmodel.AccountingViewModel

/**
 * Screen for viewing and managing accounting transactions.
 *
 * Displays a header with background image, search functionality, and transaction list table.
 *
 * @param viewModel The ViewModel managing the screen state and business logic.
 * @author Salim Ramirez
 */
@Composable
fun AccountingScreen(
    viewModel: AccountingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AccountingHeader(
            title = stringResource(R.string.accounting_screen_title),
            description = stringResource(R.string.accounting_screen_description)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TransactionSearchSection(
                searchQuery = uiState.searchQuery,
                onSearchQueryChange = viewModel::onSearchQueryChange
            )

            TransactionsTable(
                transactions = uiState.filteredTransactions,
                isLoading = uiState.isLoading,
                searchQuery = uiState.searchQuery,
                onEditClick = viewModel::onEditTransaction,
                onDeleteClick = viewModel::onDeleteTransaction,
                modifier = Modifier.weight(1f)
            )
        }
    }

    // Edit Transaction Dialog
    if (uiState.isEditDialogOpen && uiState.transactionBeingEdited != null) {
        EditTransactionDialog(
            transaction = uiState.transactionBeingEdited!!,
            onDismiss = viewModel::onCloseEditDialog,
            onSave = viewModel::onSaveTransaction,
            isSaving = uiState.isSavingEdit
        )
    }

    // Delete Confirmation Dialog
    if (uiState.isDeleteDialogOpen) {
        DeleteConfirmationDialog(
            onDismiss = viewModel::onCloseDeleteDialog,
            onConfirm = viewModel::onConfirmDelete,
            isDeleting = uiState.isDeletingTransaction
        )
    }
}

