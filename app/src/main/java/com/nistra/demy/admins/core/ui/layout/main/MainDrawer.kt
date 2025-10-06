package com.nistra.demy.admins.core.ui.layout.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.core.ui.layout.model.DrawerSection

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
    sections.forEach { section ->
        Text(
            text = section.header,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .padding(top = 8.dp, start = 20.dp, end = 20.dp, bottom = 4.dp)
        )
        section.items.forEach { destination ->
            NavigationDrawerItem(
                label = { Text(destination.label) },
                selected = destination.id == selectedId,
                onClick = { onDestinationClick(destination) },
                icon = { Icon(destination.icon, contentDescription = null) },
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }
        Spacer(Modifier.height(8.dp))
    }
}
