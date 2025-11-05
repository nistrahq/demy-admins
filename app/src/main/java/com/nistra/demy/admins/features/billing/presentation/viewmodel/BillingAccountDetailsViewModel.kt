package com.nistra.demy.admins.features.billing.presentation.viewmodel


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.core.common.SnackbarMessage
import com.nistra.demy.admins.features.billing.domain.usecase.AddInvoiceToBillingAccountUseCase
import com.nistra.demy.admins.features.billing.domain.usecase.GetBillingAccountByIdUseCase
import com.nistra.demy.admins.features.billing.presentation.navigation.BILLING_ACCOUNT_ID_ARG
import com.nistra.demy.admins.features.billing.presentation.state.BillingAccountDetailsUiState
import com.nistra.demy.admins.features.invoicing.domain.model.Invoice
import com.nistra.demy.admins.features.invoicing.presentation.model.InvoiceFormData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject
import kotlin.text.format
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import com.nistra.demy.admins.core.common.LocalizedString

@HiltViewModel
class BillingAccountDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, // Hilt nos lo da para leer los argumentos de navegación
    private val getBillingAccountByIdUseCase: GetBillingAccountByIdUseCase,
    private val addInvoiceToBillingAccountUseCase: AddInvoiceToBillingAccountUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BillingAccountDetailsUiState())
    val uiState = _uiState.asStateFlow()


    private val _invoiceFormData = MutableStateFlow(InvoiceFormData())
    val invoiceFormData = _invoiceFormData.asStateFlow()

    // Obtenemos el ID de la ruta de navegación
    private val billingAccountId: String = savedStateHandle.get<String>(BILLING_ACCOUNT_ID_ARG)!!

    init {
        loadAccountDetails()
    }

    // --- Funciones para manejar el diálogo y el formulario ---

    fun onShowAddInvoiceDialog(show: Boolean) {
        _uiState.update { it.copy(isAddInvoiceDialogVisible = show) }
        if (!show) {
            _invoiceFormData.value = InvoiceFormData()
        }
    }

    fun onInvoiceFormFieldChange(updatedData: InvoiceFormData) {
        _invoiceFormData.value = updatedData
    }



    fun onAddInvoiceConfirm() {
        val formData = _invoiceFormData.value


        if (formData.description.isBlank() ||
            formData.amount.isBlank() ||
            formData.currency.isBlank() ||
            formData.invoiceType.isBlank() ||
            formData.dueDate.isBlank())
        {
            _uiState.update {
                it.copy(snackbarMessage = SnackbarMessage(message = LocalizedString.Raw("Por favor, complete todos los campos.")))
            }
            return
        }


        val amountWithDot = formData.amount.replace(',', '.')

        // --- PASO 2: VALIDAR EL MONTO YA ESTANDARIZADO ---
        if (amountWithDot.toDoubleOrNull() == null) {
            _uiState.update {
                it.copy(snackbarMessage = SnackbarMessage(message = LocalizedString.Raw("El monto ingresado no es un número válido.")))
            }
            return
        }

        viewModelScope.launch {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val issueDateString = dateFormat.format(Calendar.getInstance().time)


            val newInvoice = Invoice(
                id = "",
                description = formData.description,

                amount = amountWithDot,
                currency = formData.currency,
                invoiceType = formData.invoiceType,
                status = "PENDING",
                issueDate = issueDateString,
                dueDate = formData.dueDate,
                billingAccountId = billingAccountId
            )


            addInvoiceToBillingAccountUseCase(billingAccountId, newInvoice)
                .onSuccess {

                    onShowAddInvoiceDialog(false)
                    loadAccountDetails()
                    _uiState.update {
                        it.copy(snackbarMessage = SnackbarMessage(message = LocalizedString.Raw("¡Factura añadida con éxito!")))
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(snackbarMessage = SnackbarMessage(message = LocalizedString.Raw("Error al guardar: ${error.message}")))
                    }
                }
        }
    }
    fun loadAccountDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            getBillingAccountByIdUseCase(billingAccountId)
                .onSuccess { account ->
                    _uiState.update {
                        it.copy(isLoading = false, billingAccount = account)
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(isLoading = false, error = error.message ?: "Error desconocido")
                    }
                }
        }
    }


}
