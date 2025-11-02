package com.nistra.demy.admins.features.billing.data.datasource.remote

import com.nistra.demy.admins.features.billing.data.remote.dto.BillingAccountResourceDto
import com.nistra.demy.admins.features.billing.data.remote.dto.CreateBillingAccountRequestDto
import com.nistra.demy.admins.features.billing.domain.model.BillingAccount

interface BillingAccountRemoteDataSource {

    suspend fun fetchBillingAccounts(): List<BillingAccountResourceDto>

    suspend fun fetchBillingAccountById(billingAccountId: String): BillingAccountResourceDto

    suspend fun addBillingAccount(request: CreateBillingAccountRequestDto): BillingAccountResourceDto
}
