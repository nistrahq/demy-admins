package com.nistra.demy.admins.features.finance.presentation.model

data class TransactionFormData(
    val type: String = "",
    val category: String = "",
    val method: String = "",
    val amount: String = "",
    val currency: String = "PEN",
    val description: String = "",
    val date: String = ""
)

