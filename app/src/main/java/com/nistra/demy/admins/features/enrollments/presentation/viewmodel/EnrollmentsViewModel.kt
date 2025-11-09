package com.nistra.demy.admins.features.enrollments.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.features.enrollments.domain.model.Enrollment
import com.nistra.demy.admins.features.enrollments.domain.model.EnrollmentStatus
import com.nistra.demy.admins.features.enrollments.domain.model.PaymentStatus
import com.nistra.demy.admins.features.enrollments.domain.usecase.CreateEnrollmentUseCase
import com.nistra.demy.admins.features.enrollments.domain.usecase.DeleteEnrollmentUseCase
import com.nistra.demy.admins.features.enrollments.domain.usecase.GetAllEnrollmentsUseCase
import com.nistra.demy.admins.features.enrollments.domain.usecase.UpdateEnrollmentUseCase
import com.nistra.demy.admins.features.enrollments.presentation.model.EnrollmentFormData
import com.nistra.demy.admins.features.enrollments.presentation.state.EnrollmentUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnrollmentsViewModel @Inject constructor(
    private val getAllEnrollmentsUseCase: GetAllEnrollmentsUseCase,
    private val createEnrollmentUseCase: CreateEnrollmentUseCase,
    private val updateEnrollmentUseCase: UpdateEnrollmentUseCase,
    private val deleteEnrollmentUseCase: DeleteEnrollmentUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(EnrollmentUiState())
    val uiState: StateFlow<EnrollmentUiState> = _uiState.asStateFlow()

    private val _formData = MutableStateFlow(EnrollmentFormData())
    val formData: StateFlow<EnrollmentFormData> = _formData.asStateFlow()

    private var allEnrollmentsCache: List<Enrollment> = emptyList()

    init {
        loadEnrollments()
    }

    fun onEnrollmentFormChange(updated: EnrollmentFormData) {
        _formData.value = updated
        _uiState.update { it.copy(isFormSuccess = false, errorMessage = null) }
    }

    fun onSaveEnrollmentClick() {
        handleSaveEnrollment()
    }

    fun onEnrollmentSelectedForEdit(enrollment: Enrollment) {
        _uiState.update { it.copy(enrollmentToEdit = enrollment, errorMessage = null) }
        _formData.value = EnrollmentFormData(
            studentId = enrollment.studentId,
            periodId = enrollment.periodId,
            scheduleId = enrollment.scheduleId,
            amount = enrollment.amount,
            currency = enrollment.currency,
            paymentStatus = enrollment.paymentStatus.name,
            enrollmentStatus = enrollment.enrollmentStatus.name
        )
    }

    fun onClearFormClick() {
        _uiState.update { it.copy(enrollmentToEdit = null, isFormSuccess = false, errorMessage = null) }
        _formData.value = EnrollmentFormData()
    }

    fun onDeleteEnrollmentClick(enrollment: Enrollment) {
        handleDeleteEnrollment(enrollment)
    }

    fun searchEnrollments(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        val filteredList = filterEnrollments(allEnrollmentsCache, query)
        _uiState.update { it.copy(enrollments = filteredList) }
    }

    private fun loadEnrollments() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            getAllEnrollmentsUseCase()
                .onSuccess { fetchedEnrollments ->
                    allEnrollmentsCache = fetchedEnrollments
                    val filteredList = filterEnrollments(allEnrollmentsCache, _uiState.value.searchQuery)
                    _uiState.update { it.copy(enrollments = filteredList, isLoading = false) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "Error al cargar matrículas.") }
                }
        }
    }

    private fun handleSaveEnrollment() {
        val existingId = _uiState.value.enrollmentToEdit?.id ?: 0L
        val data = _formData.value

        val enrollmentToSave = Enrollment(
            id = existingId,
            studentId = data.studentId!!,
            periodId = data.periodId!!,
            scheduleId = data.scheduleId!!,
            amount = data.amount,
            currency = data.currency,
            paymentStatus = PaymentStatus.valueOf(data.paymentStatus.uppercase()),
            enrollmentStatus = EnrollmentStatus.valueOf(data.enrollmentStatus.uppercase())
        )

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val result = if (enrollmentToSave.id == 0L) {
                createEnrollmentUseCase(enrollmentToSave)
            } else {
                updateEnrollmentUseCase(enrollmentToSave)
            }

            result
                .onSuccess {
                    onClearFormClick()
                    loadEnrollments()
                    _uiState.update { it.copy(isFormSuccess = true, isLoading = false) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "Error al guardar la matrícula.") }
                }
        }
    }

    private fun handleDeleteEnrollment(enrollment: Enrollment) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            deleteEnrollmentUseCase(enrollment.id)
                .onSuccess {
                    loadEnrollments()
                }
                .onFailure { e ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "Error al eliminar la matrícula.") }
                }
        }
    }

    private fun filterEnrollments(list: List<Enrollment>, query: String): List<Enrollment> {
        return if (query.isBlank()) {
            list
        } else {
            list.filter { enrollment ->
                enrollment.studentName.contains(query, ignoreCase = true)}
        }
    }
}
