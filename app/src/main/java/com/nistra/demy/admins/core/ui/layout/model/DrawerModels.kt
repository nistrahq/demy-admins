package com.nistra.demy.admins.core.ui.layout.model

import com.nistra.demy.admins.core.ui.layout.main.MainDestination

data class UserUi(
    val name: String,
    val role: String,
    val avatarResId: Int? = null
)

data class DrawerSection(
    val header: String,
    val items: List<MainDestination>
)
