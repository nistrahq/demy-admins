package com.nistra.demy.admins.features.dashboard.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.core.designsystem.components.cards.InfoCard
import com.nistra.demy.admins.core.designsystem.components.text.LabeledValue
import com.nistra.demy.admins.core.designsystem.theme.extendedColors

/**
 * Row displaying academic period details and classroom/course information.
 *
 * All three cards have consistent height using IntrinsicSize.
 *
 * @param totalStudents Total number of students in current period.
 * @param startDate Academic period start date.
 * @param endDate Academic period end date.
 * @param totalCourses Total number of courses.
 * @param totalClassrooms Total number of classrooms.
 * @param mostOverloadedTeacher Name of the most overloaded teacher.
 * @param modifier Modifier to be applied to the row.
 * @author Salim Ramirez
 */
@Composable
fun DashboardAcademicRow(
    totalStudents: Int,
    startDate: String,
    endDate: String,
    totalCourses: Int,
    totalClassrooms: Int,
    mostOverloadedTeacher: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        InfoCard(
            title = "Current Academic Period",
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            containerColor = MaterialTheme.extendedColors.warning.colorContainer.copy(alpha = 0.3f)
        ) {
            LabeledValue(
                label = "Total students",
                value = totalStudents.toString(),
                valueStyle = MaterialTheme.typography.headlineMedium
            )
            LabeledValue(
                label = "Start date",
                value = startDate,
                valueStyle = MaterialTheme.typography.titleLarge
            )
            LabeledValue(
                label = "End date",
                value = endDate,
                valueStyle = MaterialTheme.typography.titleLarge
            )
        }

        InfoCard(
            title = "Most Overloaded Teacher",
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
        ) {
            LabeledValue(
                label = "Teacher",
                value = mostOverloadedTeacher,
                valueStyle = MaterialTheme.typography.headlineSmall
            )
        }

        InfoCard(
            title = "Classrooms and Courses",
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
        ) {
            LabeledValue(
                label = "Total courses",
                value = totalCourses.toString(),
                valueStyle = MaterialTheme.typography.headlineMedium
            )
            LabeledValue(
                label = "Total classrooms",
                value = totalClassrooms.toString(),
                valueStyle = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

