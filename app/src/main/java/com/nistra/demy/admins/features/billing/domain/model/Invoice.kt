package com.nistra.demy.admins.features.billing.domain.model

data class Invoice(
    val id: String,
    val billingAccountId: String,
    val invoiceType: String,
    val amount: String,
    val currency: String,
    val description: String,
    val issueDate: String,
    val dueDate: String,
    val status: String,
)
