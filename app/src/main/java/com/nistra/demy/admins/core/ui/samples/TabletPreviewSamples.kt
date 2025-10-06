package com.nistra.demy.admins.core.ui.samples

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.nistra.demy.admins.core.ui.layout.BaseLayout
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
    BaseLayout(title = "Light Preview") {
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
    BaseLayout(title = "Dark Preview") {
        Text("Preview in Dark Mode 🌙")
    }
}
