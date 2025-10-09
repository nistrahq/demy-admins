@file:OptIn(ExperimentalMaterial3Api::class)
package com.nistra.demy.admins.features.teachers.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.core.design.theme.DemyTheme
import com.nistra.demy.admins.core.ui.preview.MainLayoutPreviewContainer
import com.nistra.demy.admins.core.ui.preview.TabletPreviewLight
import com.nistra.demy.admins.features.teachers.presentation.ui.components.RegisterTeacher
import com.nistra.demy.admins.features.teachers.presentation.ui.components.SectionDescription
import com.nistra.demy.admins.features.teachers.data.local.models.Teacher
import com.nistra.demy.admins.features.teachers.presentation.ui.components.TeacherList

@Composable
fun TeachersScreen() {
    // Lista simulada (temporal)
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
    //  Contenedor principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.Start
    ){
        // Bloque descriptivo (arriba)
       /* SectionDescription(
            title = "Manage your teachers",
            description = "In this section you can control the teachers' registration, this way you will have more efficient control over the teachers of your academy, you can also view them",
            modifier = Modifier.fillMaxWidth()
        ) */

        // Espaciado entre bloque y contenido principal
        Spacer(modifier = Modifier.height(4.dp))

        //  Contenido principal (form + lista)
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Formulario más compacto
            RegisterTeacher(
                modifier = Modifier
                    .widthIn(max = 440.dp)
                    .wrapContentHeight()
                    .background(Color.Transparent)
            )

            // Lista de profesores
            TeacherList(
                teachers = mockTeachers,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight() //
            )
        }

    }

}

@TabletPreviewLight
@Composable
fun TeachersScreenPreview() {
    MainLayoutPreviewContainer(title = "Teachers Management") {
        DemyTheme {
            TeachersScreen()
        }
    }
}
