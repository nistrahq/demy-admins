@file:OptIn(ExperimentalMaterial3Api::class)
package com.nistra.demy.admins.features.teachers.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nistra.demy.admins.core.ui.preview.TabletPreviewLight

@Composable
fun RegisterTeacher(
    modifier: Modifier = Modifier,
    onRegisterClick: () -> Unit = {}
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Men") }

    //  Card principal con altura adaptable
    Box(
        modifier = modifier
            .background(Color(0xFFDAD9E7), RoundedCornerShape(16.dp))
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            //  Header azul
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF3D4BB3), RoundedCornerShape(12.dp))
                    .padding(vertical = 20.dp, horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Register new teacher",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            //  Campos
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedTextField(
                        value = firstName,
                        onValueChange = { firstName = it },
                        label = { Text("First name") },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        label = { Text("Last name") },
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = { Text("Phone number") },
                        modifier = Modifier.weight(1f)
                    )
                }

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            //  Selector de género
            GenderToggle(
                selected = gender,
                onSelected = { gender = it },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .align(Alignment.CenterHorizontally)
                    .heightIn(min = 40.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Botón Register (proporcional y visible)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = onRegisterClick,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3D4BB3)),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 6.dp),
                    modifier = Modifier
                        .widthIn(min = 120.dp)
                        .height(38.dp) //
                ) {
                    Text(
                        text = "Register",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun GenderToggle(
    selected: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(Color(0xFFE8E6F0), RoundedCornerShape(24.dp))
            .padding(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        GenderOption(
            text = "Men",
            selected = selected == "Men",
            onClick = { onSelected("Men") }
        )
        GenderOption(
            text = "Women",
            selected = selected == "Women",
            onClick = { onSelected("Women") }
        )
    }
}

@Composable
private fun RowScope.GenderOption(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) Color(0xFF3D4BB3) else Color.White,
            contentColor = if (selected) Color.White else Color.Black
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .height(36.dp)
            .weight(1f)
    ) {
        Text(text)
    }
}

@TabletPreviewLight
@Composable
fun RegisterTeacherPreview(){
    RegisterTeacher {  }

}
