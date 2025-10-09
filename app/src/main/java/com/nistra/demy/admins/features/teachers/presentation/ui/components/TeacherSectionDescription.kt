package com.nistra.demy.admins.features.teachers.presentation.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SectionDescription(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFE6E5F2), RoundedCornerShape(12.dp))
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Text(
            text = title,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = description,
            color = Color.DarkGray,
            style = MaterialTheme.typography.bodyMedium,
            lineHeight = 18.sp
        )
    }
}
