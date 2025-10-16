package com.nistra.demy.admins.features.auth.data.remote.dto

data class SignInResponseDto(
    val id: String,
    val email: String,
    val token: String
)
