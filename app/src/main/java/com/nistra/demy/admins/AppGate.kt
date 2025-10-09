package com.nistra.demy.admins

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import kotlin.math.min

/**
 * Restricts app usage to tablets in landscape mode.
 *
 * Checks the device's smallest width in dp using [LocalWindowInfo].
 * If it's under 600 dp, the activity closes and shows a warning message.
 * Otherwise, renders the given [content].
 *
 * @param content Main composable content shown on supported devices.
 * @author Salim Ramirez
 */
@Composable
fun AppGate(content: @Composable () -> Unit) {
    val activity = LocalActivity.current
    val windowInfo = LocalWindowInfo.current
    val density = LocalDensity.current

    val widthPx = windowInfo.containerSize.width
    val heightPx = windowInfo.containerSize.height
    val smallestWidthDp = with(density) { min(widthPx, heightPx).toDp().value }

    if (smallestWidthDp < 600f) {
        LaunchedEffect(Unit) { activity?.finish() }
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = stringResource(id = R.string.tablet_only_message))
        }
    } else {
        content()
    }
}
