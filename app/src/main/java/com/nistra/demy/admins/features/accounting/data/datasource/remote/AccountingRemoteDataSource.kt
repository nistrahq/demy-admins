package com.nistra.demy.admins.features.accounting.data.datasource.remote

import com.nistra.demy.admins.features.accounting.data.remote.dto.TransactionResourceDto
import com.nistra.demy.admins.features.accounting.data.remote.dto.UpdateTransactionRequestDto

interface AccountingRemoteDataSource {

    suspend fun fetchTransactions(): List<TransactionResourceDto>

    suspend fun fetchTransactionById(
        transactionId: Long
    ): TransactionResourceDto

    suspend fun updateTransaction(
        transactionId: Long,
        request: UpdateTransactionRequestDto
    ): TransactionResourceDto

    suspend fun deleteTransaction(
        transactionId: Long
    )
}

