package com.nistra.demy.admins.features.invoicing.data.repositories


import com.nistra.demy.admins.features.billing.data.datasource.remote.BillingAccountRemoteDataSource
import com.nistra.demy.admins.features.invoicing.data.mapper.toDomain
import com.nistra.demy.admins.features.invoicing.domain.model.Invoice
import com.nistra.demy.admins.features.invoicing.domain.repository.InvoiceRepository
import javax.inject.Inject


class InvoiceRepositoryImpl @Inject constructor(
    private val remoteDataSource: BillingAccountRemoteDataSource
) : InvoiceRepository {


    override suspend fun getAllInvoicesByBillingAccountId(billingAccountId: String): List<Invoice> {
        val billingAccountDto = remoteDataSource.fetchBillingAccountById(billingAccountId)
        return billingAccountDto.invoices.map { it.toDomain(billingAccountId = billingAccountId) }
    }


    override suspend fun markInvoiceAsPaid(billingAccountId: String, invoiceId: String): Invoice {
        val updatedInvoiceDto = remoteDataSource.markInvoiceAsPaid(billingAccountId, invoiceId)
        return updatedInvoiceDto.toDomain(billingAccountId = billingAccountId)
    }
}
