package com.nistra.demy.admins.features.billing.data.mapper

import com.nistra.demy.admins.features.billing.data.remote.dto.InvoiceResourceDto
import com.nistra.demy.admins.features.billing.domain.model.Invoice

fun InvoiceResourceDto.toDomain(billingAccountId: String ): Invoice {
    return Invoice(
        id = this.id,
        invoiceType = this.invoiceType,
        amount = this.amount,
        currency = this.currency,
        description = this.description,
        issueDate = this.issueDate,
        dueDate = this.dueDate,
        status = this.status,

        billingAccountId = billingAccountId
    )
}
