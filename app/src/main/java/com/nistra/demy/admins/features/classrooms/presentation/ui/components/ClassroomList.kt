package com.nistra.demy.admins.features.classrooms.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.features.classrooms.domain.models.Classroom

@Composable
fun ClassroomList(
    modifier: Modifier = Modifier,
    classrooms: List<Classroom>,
    onClassroomSelected: (Classroom) -> Unit,
    onDeleteClassroom: (Classroom) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    searchQuery: String
) {
    Card(
        modifier = modifier.fillMaxHeight(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // Campo de Búsqueda
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                label = { Text("Buscar aulas por código") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            // Lista de Aulas
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(classrooms, key = { it.id }) { classroom ->
                    ClassroomListItem(
                        classroom = classroom,
                        onEditClick = { onClassroomSelected(classroom) },
                        onDeleteClick = { onDeleteClassroom(classroom) }
                    )
                }
            }
        }
    }
}
