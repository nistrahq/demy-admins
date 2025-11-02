package com.nistra.demy.admins.features.billing.data.remote.dto

data class BillingAccountResourceDto(
    val id: String,
    val studentId: String,
    val academyId: String,
    val invoices: List<InvoiceResourceDto>
)
