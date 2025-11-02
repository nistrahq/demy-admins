package com.nistra.demy.admins.features.billing.data.datasource.remote

import com.nistra.demy.admins.core.common.safeApiCall
import com.nistra.demy.admins.features.billing.data.remote.api.BillingAccountsApi
import com.nistra.demy.admins.features.billing.data.remote.dto.BillingAccountResourceDto
import com.nistra.demy.admins.features.billing.data.remote.dto.CreateBillingAccountRequestDto
import javax.inject.Inject

class BillingAccountRemoteDataSourceImpl @Inject constructor(
    private val api: BillingAccountsApi
): BillingAccountRemoteDataSource {
    override suspend fun fetchBillingAccounts(): List<BillingAccountResourceDto> {
        return safeApiCall(endpoint = "billing-accounts") {api.getAllBillingAccounts()}
    }

    override suspend fun fetchBillingAccountById(billingAccountId: String): BillingAccountResourceDto {
        return safeApiCall(endpoint = "billing-accounts/{billingAccountId}") { api.getBillingAccountById(billingAccountId) }
    }

    override suspend fun addBillingAccount( request: CreateBillingAccountRequestDto): BillingAccountResourceDto {
        return safeApiCall(endpoint = "billing-accounts") {  api.createBillingAccount(request) }
    }
}
