package com.nistra.demy.admins.core.ui.preview

import androidx.compose.runtime.Composable
import com.nistra.demy.admins.core.design.theme.DemyTheme
import com.nistra.demy.admins.core.ui.components.MainDestination
import com.nistra.demy.admins.core.ui.layout.MainLayout
import com.nistra.demy.admins.core.model.DrawerSection
import com.nistra.demy.admins.core.model.UserUi

/**
 * Defaults for previewing the MainLayout composable.
 *
 * Includes a sample user and drawer sections.
 *
 * @author Salim Ramirez
 */
object MainLayoutPreviewDefaults {
    val user = UserUi(name = "Salim", role = "Administrator", avatarResId = null)
    val sections = listOf(
        DrawerSection("Overview", listOf(MainDestination.Dashboard)),
        DrawerSection("Academy", listOf(MainDestination.Teachers, MainDestination.Students))
    )
}

/**
 * Container for previewing the MainLayout composable.
 *
 * Sets up the DemyTheme and provides default parameters.
 *
 * @param title The title to display in the top app bar.
 * @param appName The name of the application.
 * @param selectedDestinationId The currently selected destination ID.
 * @param content The main content composable.
 * @author Salim Ramirez
 */
@Composable
fun MainLayoutPreviewContainer(
    title: String = "Preview",
    appName: String = "Demy Admins",
    selectedDestinationId: String = MainDestination.Dashboard.id,
    content: @Composable () -> Unit
) {
    DemyTheme {
        MainLayout(
            title = title,
            user = MainLayoutPreviewDefaults.user,
            showBackButton = false,
            onBackClick = null,
            appName = appName,
            drawerSections = MainLayoutPreviewDefaults.sections,
            selectedDestinationId = selectedDestinationId,
            onDestinationClick = { },
            content = content
        )
    }
}
