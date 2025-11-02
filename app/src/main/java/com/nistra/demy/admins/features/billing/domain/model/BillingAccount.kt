package com.nistra.demy.admins.features.billing.domain.model

data class BillingAccount(
    val id: String,
    val studentId: String,
    val invoices: List<Invoice>,
    val academyId: String

)
