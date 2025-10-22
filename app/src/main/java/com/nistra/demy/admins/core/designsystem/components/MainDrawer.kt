package com.nistra.demy.admins.core.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.core.designsystem.model.DrawerSection
import com.nistra.demy.admins.core.navigation.model.MainDestination

@Composable
fun DrawerHeader(appName: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Image(painterResource(id = R.drawable.ic_app_logo), contentDescription = null, modifier = Modifier.size(40.dp))
        Text(
            text = appName,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun DrawerContent(
    sections: List<DrawerSection>,
    selectedId: String,
    onDestinationClick: (MainDestination) -> Unit
) {
    val listState = rememberLazyListState()

    val canScrollBackward by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 || listState.firstVisibleItemScrollOffset > 0 }
    }
    val canScrollForward by remember {
        derivedStateOf {
            val info = listState.layoutInfo
            val visible = info.visibleItemsInfo
            if (visible.isEmpty()) false
            else {
                val last = visible.last()
                last.index < info.totalItemsCount - 1 ||
                    (last.offset + last.size) > info.viewportEndOffset
            }
        }
    }

    Box {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            sections.forEach { section ->
                item(key = "header-${section.header}") {
                    Text(
                        text = section.header,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(top = 8.dp, start = 20.dp, end = 20.dp, bottom = 4.dp)
                    )
                }
                items(
                    items = section.items,
                    key = { it.id }
                ) { destination ->
                    NavigationDrawerItem(
                        label = { Text(destination.label, style = MaterialTheme.typography.bodyMedium) },
                        selected = destination.id == selectedId,
                        onClick = { onDestinationClick(destination) },
                        icon = { Icon(destination.icon, contentDescription = null) },
                        modifier = Modifier.padding(horizontal = 12.dp),
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f),
                            selectedIconColor = MaterialTheme.colorScheme.secondary,
                            selectedTextColor = MaterialTheme.colorScheme.secondary
                        )
                    )
                }
                item(key = "spacer-${section.header}") { Spacer(Modifier.height(8.dp)) }
            }
            item(key = "bottom-padding") { Spacer(Modifier.height(8.dp)) }
        }

        TopEdgeFade(
            visible = canScrollBackward,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        BottomEdgeFade(
            visible = canScrollForward,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
