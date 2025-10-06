package com.nistra.demy.admins.core.ui.samples

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.nistra.demy.admins.core.ui.layout.main.MainDestination
import com.nistra.demy.admins.core.ui.layout.main.MainLayout
import com.nistra.demy.admins.core.ui.layout.model.DrawerSection
import com.nistra.demy.admins.core.ui.layout.model.UserUi
import com.nistra.demy.admins.core.ui.preview.TabletPreviewDark
import com.nistra.demy.admins.core.ui.preview.TabletPreviewLight

/**
 * Demonstrates how to use [TabletPreviewLight].
 *
 * @author Salim Ramirez
 */
@TabletPreviewLight
@Composable
fun TabletLightPreviewSample() {
    MainLayout(
        title = "Dashboard",
        user = UserUi(
            name = "Paul",
            role = "Administrator",
            avatarResId = null
        ),
        showBackButton = true,
        onBackClick = null,
        appName = "Demy Admins",
        drawerSections = listOf(
            DrawerSection(
                header = "Overview",
                items = listOf(MainDestination.Dashboard)
            ),
            DrawerSection(
                header = "Academy",
                items = listOf(MainDestination.Teachers, MainDestination.Students)
            )
        ),
        selectedDestinationId = MainDestination.Dashboard.id,
        onDestinationClick = { }
    ) {
        Text("Preview in Light Mode 🌞")
    }
}

/**
 * Demonstrates how to use [TabletPreviewDark].
 *
 * @author Salim Ramirez
 */
@TabletPreviewDark
@Composable
fun TabletDarkPreviewSample() {
    MainLayout(
        title = "Dashboard",
        user = UserUi(
            name = "Daniel",
            role = "Administrator",
            avatarResId = null
        ),
        showBackButton = true,
        onBackClick = null,
        appName = "Demy Admins",
        drawerSections = listOf(
            DrawerSection(
                header = "Overview",
                items = listOf(MainDestination.Dashboard)
            ),
            DrawerSection(
                header = "Academy",
                items = listOf(MainDestination.Teachers, MainDestination.Students)
            )
        ),
        selectedDestinationId = MainDestination.Dashboard.id,
        onDestinationClick = { }
    ) {
        Text("Preview in Dark Mode 🌙")
    }
}
