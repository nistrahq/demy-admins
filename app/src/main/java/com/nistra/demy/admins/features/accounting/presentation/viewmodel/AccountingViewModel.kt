package com.nistra.demy.admins.features.accounting.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.features.accounting.domain.model.Transaction
import com.nistra.demy.admins.features.accounting.domain.usecase.DeleteTransactionUseCase
import com.nistra.demy.admins.features.accounting.domain.usecase.GetAllTransactionsUseCase
import com.nistra.demy.admins.features.accounting.domain.usecase.UpdateTransactionUseCase
import com.nistra.demy.admins.features.accounting.presentation.state.AccountingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the accounting transactions list screen.
 *
 * Manages the state of transactions list, search filtering, and user actions.
 *
 * @author Salim Ramirez
 */
@HiltViewModel
class AccountingViewModel @Inject constructor(
    private val getAllTransactionsUseCase: GetAllTransactionsUseCase,
    private val updateTransactionUseCase: UpdateTransactionUseCase,
    private val deleteTransactionUseCase: DeleteTransactionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AccountingUiState())
    val uiState: StateFlow<AccountingUiState> = _uiState.asStateFlow()

    init {
        loadTransactions()
    }

    /**
     * Loads all transactions from the repository.
     */
    private fun loadTransactions() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            getAllTransactionsUseCase()
                .onSuccess { transactions ->
                    _uiState.update {
                        it.copy(
                            transactions = transactions,
                            filteredTransactions = transactions,
                            isLoading = false
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.message
                        )
                    }
                }
        }
    }

    /**
     * Updates the search query and filters transactions.
     *
     * @param query The search query string.
     */
    fun onSearchQueryChange(query: String) {
        _uiState.update { currentState ->
            val filtered = if (query.isBlank()) {
                currentState.transactions
            } else {
                currentState.transactions.filter { transaction ->
                    transaction.type.contains(query, ignoreCase = true) ||
                    transaction.category.contains(query, ignoreCase = true) ||
                    transaction.method.contains(query, ignoreCase = true) ||
                    transaction.description?.contains(query, ignoreCase = true) == true
                }
            }

            currentState.copy(
                searchQuery = query,
                filteredTransactions = filtered
            )
        }
    }

    /**
     * Handles edit action for a transaction.
     * Opens the edit dialog with the selected transaction.
     *
     * @param transactionId The ID of the transaction to edit.
     */
    fun onEditTransaction(transactionId: String) {
        val transaction = _uiState.value.transactions.find { it.id == transactionId }
        if (transaction != null) {
            _uiState.update {
                it.copy(
                    isEditDialogOpen = true,
                    transactionBeingEdited = transaction
                )
            }
        }
    }

    /**
     * Closes the edit dialog.
     */
    fun onCloseEditDialog() {
        _uiState.update {
            it.copy(
                isEditDialogOpen = false,
                transactionBeingEdited = null,
                isSavingEdit = false
            )
        }
    }

    /**
     * Saves the edited transaction.
     *
     * @param updatedTransaction The updated transaction data.
     */
    fun onSaveTransaction(updatedTransaction: Transaction) {
        viewModelScope.launch {
            val transactionId = updatedTransaction.id.toLongOrNull() ?: return@launch

            _uiState.update { it.copy(isSavingEdit = true, errorMessage = null) }

            updateTransactionUseCase(transactionId, updatedTransaction)
                .onSuccess {
                    _uiState.update {
                        it.copy(
                            isEditDialogOpen = false,
                            transactionBeingEdited = null,
                            isSavingEdit = false
                        )
                    }
                    // Reload transactions after successful update
                    loadTransactions()
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isSavingEdit = false,
                            errorMessage = error.message
                        )
                    }
                }
        }
    }

    /**
     * Opens the delete confirmation dialog.
     *
     * @param transactionId The ID of the transaction to delete.
     */
    fun onDeleteTransaction(transactionId: String) {
        _uiState.update {
            it.copy(
                isDeleteDialogOpen = true,
                transactionToDelete = transactionId
            )
        }
    }

    /**
     * Closes the delete confirmation dialog.
     */
    fun onCloseDeleteDialog() {
        _uiState.update {
            it.copy(
                isDeleteDialogOpen = false,
                transactionToDelete = null,
                isDeletingTransaction = false
            )
        }
    }

    /**
     * Confirms and executes the delete operation.
     */
    fun onConfirmDelete() {
        viewModelScope.launch {
            val transactionId = _uiState.value.transactionToDelete ?: return@launch
            val id = transactionId.toLongOrNull() ?: return@launch

            _uiState.update { it.copy(isDeletingTransaction = true, errorMessage = null) }

            deleteTransactionUseCase(id)
                .onSuccess {
                    _uiState.update {
                        it.copy(
                            isDeleteDialogOpen = false,
                            transactionToDelete = null,
                            isDeletingTransaction = false
                        )
                    }
                    // Reload transactions after successful deletion
                    loadTransactions()
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isDeletingTransaction = false,
                            errorMessage = error.message
                        )
                    }
                }
        }
    }
}

