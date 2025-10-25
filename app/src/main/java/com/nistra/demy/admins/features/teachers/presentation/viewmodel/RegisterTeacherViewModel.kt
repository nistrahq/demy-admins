package com.nistra.demy.admins.features.teachers.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nistra.demy.admins.features.teachers.domain.model.Teacher
import com.nistra.demy.admins.features.teachers.domain.usecase.RegisterTeacherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterTeacherViewModel @Inject constructor(
    private val registerTeacherUseCase: RegisterTeacherUseCase
) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    fun registerTeacher(teacher: Teacher) {
        viewModelScope.launch {
            _isLoading.value = true
            registerTeacherUseCase(teacher)
            _isLoading.value = false
        }
    }
}
