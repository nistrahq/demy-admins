package com.nistra.demy.admins.features.billing.data.remote.api

import com.nistra.demy.admins.features.billing.data.remote.dto.BillingAccountResourceDto
import com.nistra.demy.admins.features.billing.data.remote.dto.CreateBillingAccountRequestDto
import com.nistra.demy.admins.features.invoicing.data.remote.dto.InvoiceResourceDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BillingAccountsApi {

    @GET("billing-accounts")
    suspend fun getAllBillingAccounts(): List<BillingAccountResourceDto>

    @GET("billing-accounts/{billingAccountId}")
    suspend fun getBillingAccountById(@Path("billingAccountId") billingAccountId: String ): BillingAccountResourceDto

    @POST("billing-accounts")
    suspend fun createBillingAccount(@Body request: CreateBillingAccountRequestDto): BillingAccountResourceDto

    @POST("billing-accounts/{billingAccountId}/invoices")
    suspend fun addInvoiceToBillingAccount(
        @Path("billingAccountId") billingAccountId: String,
        @Body invoiceDto: InvoiceResourceDto): BillingAccountResourceDto
}
