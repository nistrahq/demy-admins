package com.nistra.demy.admins.core.designsystem.model

import com.nistra.demy.admins.core.navigation.model.DrawerDestination

data class UserUi(
    val name: String,
    val role: String,
    val avatarResId: Int? = null
)

data class DrawerSection(
    val header: String,
    val items: List<DrawerDestination>
)
