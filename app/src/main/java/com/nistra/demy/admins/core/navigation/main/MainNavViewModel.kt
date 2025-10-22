package com.nistra.demy.admins.core.navigation.main

import androidx.lifecycle.ViewModel
import com.nistra.demy.admins.core.data.local.SessionPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainNavViewModel @Inject constructor(
    val sessionPreferences: SessionPreferences
) : ViewModel()
