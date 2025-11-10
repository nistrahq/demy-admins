package com.nistra.demy.admins.features.finance.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.nistra.demy.admins.features.finance.domain.usecase.GetAllTransactionsUseCase
import com.nistra.demy.admins.features.finance.domain.usecase.GetTransactionByIdUseCase
import com.nistra.demy.admins.features.finance.domain.usecase.RegisterTransactionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FinanceViewModel @Inject constructor(
    private val registerTransactionUseCase: RegisterTransactionUseCase,
    private val getAllTransactionsUseCase: GetAllTransactionsUseCase,
    private val getTransactionByIdUseCase: GetTransactionByIdUseCase
) : ViewModel() {

}
