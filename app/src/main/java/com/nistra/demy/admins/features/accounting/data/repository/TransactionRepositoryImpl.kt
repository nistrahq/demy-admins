package com.nistra.demy.admins.features.accounting.data.repository

import com.nistra.demy.admins.features.accounting.data.datasource.remote.AccountingRemoteDataSource
import com.nistra.demy.admins.features.accounting.data.mapper.toDomain
import com.nistra.demy.admins.features.accounting.data.mapper.toUpdateRequestDto
import com.nistra.demy.admins.features.accounting.domain.model.Transaction
import com.nistra.demy.admins.features.accounting.domain.repository.TransactionRepository
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val remoteDataSource: AccountingRemoteDataSource
) : TransactionRepository {

    override suspend fun getAllTransactions(): List<Transaction> {
        val transactionsResponse = remoteDataSource.fetchTransactions()
        return transactionsResponse.map { it.toDomain() }
    }

    override suspend fun getTransactionById(transactionId: Long): Transaction {
        val transactionResourceDto = remoteDataSource.fetchTransactionById(transactionId)
        return transactionResourceDto.toDomain()
    }

    override suspend fun updateTransaction(transactionId: Long, transaction: Transaction): Transaction {
        val requestDto = transaction.toUpdateRequestDto()
        val responseDto = remoteDataSource.updateTransaction(transactionId, requestDto)
        return responseDto.toDomain()
    }

    override suspend fun deleteTransaction(transactionId: Long) {
        remoteDataSource.deleteTransaction(transactionId)
    }
}

