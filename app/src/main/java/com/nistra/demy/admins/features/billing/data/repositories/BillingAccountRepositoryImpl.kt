package com.nistra.demy.admins.features.billing.data.repositories

import com.nistra.demy.admins.features.billing.data.datasource.remote.BillingAccountRemoteDataSource
import com.nistra.demy.admins.features.billing.data.mapper.toDomain
import com.nistra.demy.admins.features.billing.data.mapper.toRequestDto
import com.nistra.demy.admins.features.billing.domain.model.BillingAccount
import com.nistra.demy.admins.features.billing.domain.repository.BillingAccountRepository
import javax.inject.Inject

class BillingAccountRepositoryImpl @Inject constructor(
    private val billingAccountsRemoteDataSource: BillingAccountRemoteDataSource
): BillingAccountRepository {

    override suspend fun  getAllBillingAccounts(): List<BillingAccount> {
        val billingAccountsResponse = billingAccountsRemoteDataSource.fetchBillingAccounts()
        return billingAccountsResponse.map { it.toDomain() }
    }

    override suspend fun getBillingAccountById(billingAccountId: String): BillingAccount {
        val billingAccountResponse = billingAccountsRemoteDataSource.fetchBillingAccountById(billingAccountId)
        return billingAccountResponse.toDomain()
    }

    override suspend fun  createBillingAccount(billingAccount: BillingAccount): BillingAccount {
        val createdBillingAccountResourceDto = billingAccountsRemoteDataSource.addBillingAccount(billingAccount.toRequestDto())
        return  createdBillingAccountResourceDto.toDomain()
    }
}
