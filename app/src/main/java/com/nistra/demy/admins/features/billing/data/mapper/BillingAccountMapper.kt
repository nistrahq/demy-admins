package com.nistra.demy.admins.features.billing.data.mapper

import com.nistra.demy.admins.features.billing.data.remote.dto.BillingAccountResourceDto
import com.nistra.demy.admins.features.billing.data.remote.dto.CreateBillingAccountRequestDto
import com.nistra.demy.admins.features.billing.domain.model.BillingAccount
import com.nistra.demy.admins.features.invoicing.data.mapper.toDomain

fun BillingAccountResourceDto.toDomain(): BillingAccount {
    val accountId = this.id

    return BillingAccount (
        id = this.id,
        studentId = this.studentId,
        invoices = this.invoices.map { it.toDomain(billingAccountId = accountId) },
        academyId = this.academyId

    )
}


fun BillingAccount.toRequestDto(): CreateBillingAccountRequestDto {
    return CreateBillingAccountRequestDto(
        studentId = this.studentId
    )
}


