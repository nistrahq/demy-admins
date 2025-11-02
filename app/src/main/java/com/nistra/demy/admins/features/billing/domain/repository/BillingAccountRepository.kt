package com.nistra.demy.admins.features.billing.domain.repository

import com.nistra.demy.admins.features.billing.domain.model.BillingAccount

interface BillingAccountRepository {

    suspend fun createBillingAccount(billingAccount: BillingAccount): BillingAccount

    suspend fun getBillingAccountById(billingAccountId: String): BillingAccount

    suspend fun getAllBillingAccounts(): List<BillingAccount>
}
