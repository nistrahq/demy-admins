package com.nistra.demy.admins.core.designsystem.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.core.designsystem.theme.DemyTheme
import com.nistra.demy.admins.core.designsystem.components.UserChip
import com.nistra.demy.admins.core.designsystem.components.DrawerContent
import com.nistra.demy.admins.core.designsystem.components.DrawerHeader
import com.nistra.demy.admins.core.navigation.model.DrawerDestination
import com.nistra.demy.admins.core.designsystem.model.DrawerSection
import com.nistra.demy.admins.core.designsystem.model.UserUi
import com.nistra.demy.admins.core.designsystem.preview.TabletPreviewDark
import com.nistra.demy.admins.core.designsystem.preview.TabletPreviewLight

/**
 * Main layout composable with a permanent navigation drawer and a top app bar.
 *
 * Includes a user info chip in the app bar.
 * Designed for tablet devices in landscape orientation.
 *
 * @param title Title shown in the top app bar.
 * @param user User information for the user chip.
 * @param showBackButton Whether to show a back button in the app bar.
 * @param onBackClick Callback when the back button is clicked.
 * @param appName Name of the application shown in the drawer header.
 * @param drawerSections Sections and items for the navigation drawer.
 * @param selectedDestinationId Currently selected destination ID.
 * @param onDestinationClick Callback when a navigation item is clicked.
 * @param content Main content composable.
 * @author Salim Ramirez
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(
    title: String,
    user: UserUi,
    showBackButton: Boolean = false,
    onBackClick: (() -> Unit)? = null,
    appName: String,
    drawerSections: List<DrawerSection>,
    selectedDestinationId: String,
    onDestinationClick: (DrawerDestination) -> Unit,
    content: @Composable () -> Unit
) {
    PermanentNavigationDrawer(
        drawerContent = {
            PermanentDrawerSheet(modifier = Modifier.width(304.dp)) {
                DrawerHeader(appName = appName)
                Spacer(Modifier.height(16.dp))
                DrawerContent(
                    sections = drawerSections,
                    selectedId = selectedDestinationId,
                    onDestinationClick = onDestinationClick
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        if (showBackButton && onBackClick != null) {
                            IconButton(onClick = onBackClick) {
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = null
                                )
                            }
                        }
                    },
                    actions = {
                        UserChip(user = user)
                    }
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .fillMaxSize()
            ) {
                content()
            }
        }
    }
}

/**
 * Light theme preview of the MainLayout composable.
 *
 * @author Salim Ramirez
 */
@TabletPreviewLight
@TabletPreviewDark
@Composable
fun MainLayoutPreview() {
    val sections = listOf(
        DrawerSection(
            header = "Overview",
            items = listOf(DrawerDestination.Dashboard)
        ),
        DrawerSection(
            header = "Academy",
            items = listOf(DrawerDestination.Teachers, DrawerDestination.Students)
        ),
        DrawerSection(
            header = "Classes",
            items = listOf(DrawerDestination.Periods, DrawerDestination.Courses, DrawerDestination.Classrooms, DrawerDestination.Schedules)
        ),
        DrawerSection(
            header = "Management",
            items = listOf(DrawerDestination.Enrollments, DrawerDestination.Scheduling, DrawerDestination.Billing, DrawerDestination.Invoices, DrawerDestination.Finance)
        ),
        DrawerSection(
            header = "General",
            items = listOf(DrawerDestination.Settings, DrawerDestination.Help, DrawerDestination.LogOut)
        )
    )
    DemyTheme {
        MainLayout(
            title = "Dashboard",
            user = UserUi(
                name = "Salim",
                role = "Administrator",
                avatarResId = null
            ),
            showBackButton = true,
            onBackClick = null,
            appName = "Demy Admins",
            drawerSections = sections,
            selectedDestinationId = DrawerDestination.Dashboard.id,
            onDestinationClick = { }
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Dashboard content")
            }
        }
    }
}
