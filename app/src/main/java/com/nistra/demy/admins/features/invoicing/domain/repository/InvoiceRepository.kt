package com.nistra.demy.admins.features.invoicing.domain.repository

import com.nistra.demy.admins.features.invoicing.domain.model.Invoice

interface InvoiceRepository {

    suspend fun getAllInvoicesByBillingAccountId(billingAccountId: String): List<Invoice>

}
