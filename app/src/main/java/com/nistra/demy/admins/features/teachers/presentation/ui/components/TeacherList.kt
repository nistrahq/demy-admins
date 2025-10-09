@file:OptIn(ExperimentalMaterial3Api::class)

package com.nistra.demy.admins.features.teachers.presentation.ui.components
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nistra.demy.admins.core.ui.preview.TabletPreviewLight
import com.nistra.demy.admins.features.teachers.data.local.models.Teacher


@Composable
fun TeacherList(
    teachers: List<Teacher>,
    modifier: Modifier = Modifier,
    onEditClick: (Teacher) -> Unit = {},
    onDeleteClick: (Teacher) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .background(Color(0xFFDAD9E7), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        //  Barra de búsqueda
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            placeholder = { Text("Hinted search text") },
            singleLine = true,
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE8E6F0), RoundedCornerShape(50))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de profesores
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE8E6F0), RoundedCornerShape(16.dp))
                .padding(8.dp)
        ) {
            val filteredList = teachers.filter {
                it.name.contains(searchQuery, ignoreCase = true)
            }

            items(filteredList) { teacher ->
                TeacherListItem(
                    teacher = teacher,
                    onEditClick = onEditClick,
                    onDeleteClick = onDeleteClick
                )
            }
        }
    }
}

//  Item individual de la lista
@Composable
fun TeacherListItem(
    teacher: Teacher,
    onEditClick: (Teacher) -> Unit,
    onDeleteClick: (Teacher) -> Unit
) {
    Row(
        modifier = Modifier
            .background(Color(0xFFDAD9E7), RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar circular
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(Color(0xFF8E63D4), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = teacher.name.first().uppercase(),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Nombre
        Text(
            text = teacher.name,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
            color = Color.Black,
            modifier = Modifier.weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        // Icono editar
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Edit",
            tint = Color.Black,
            modifier = Modifier
                .size(20.dp)
                .clickable { onEditClick(teacher) }
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Icono eliminar
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete",
            tint = Color.Black,
            modifier = Modifier
                .size(20.dp)
                .clickable { onDeleteClick(teacher) }
        )
    }
}

// 🔹 Preview visual simple
@TabletPreviewLight
@Composable
fun TeacherListPreview() {
    val mockTeachers = listOf(
        Teacher(1, "Edward Crispin"),
        Teacher(2, "Adriana Salte"),
        Teacher(3, "Anjali Ramirez"),
        Teacher(4, "Alexander Aquino"),
        Teacher(5, "Irvin Quispe"),
        Teacher(6, "Gerardo Chavez"),
        Teacher(7, "Silvia Benites"),
        Teacher(8, "Efrain Ponce"),
        Teacher(9, "Cesar Meza"),
        Teacher(10, "Paul Huaman"),
        Teacher(11, "Gabriela Melendez"),
        Teacher(12, "Sofia Mia"),
        Teacher(13, "Enrique Ramos"),
        Teacher(14, "Jorge Luis")
    )

    TeacherList(
        teachers = mockTeachers,
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp)
            .padding(16.dp)
    )
}
