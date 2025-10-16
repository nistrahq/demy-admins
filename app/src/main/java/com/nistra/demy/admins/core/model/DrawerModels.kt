package com.nistra.demy.admins.core.model

import com.nistra.demy.admins.core.ui.components.MainDestination

data class UserUi(
    val name: String,
    val role: String,
    val avatarResId: Int? = null
)

data class DrawerSection(
    val header: String,
    val items: List<MainDestination>
)
