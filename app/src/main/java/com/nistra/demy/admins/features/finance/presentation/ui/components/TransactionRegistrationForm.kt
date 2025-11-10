package com.nistra.demy.admins.features.finance.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.R
import com.nistra.demy.admins.core.designsystem.components.forms.FormCard
import com.nistra.demy.admins.features.finance.presentation.model.TransactionFormData

/**
 * Form component for transaction registration.
 *
 * Provides input fields for transaction information including type, category,
 * method, amount, currency, description, and date.
 * Uses the generic FormCard component for consistent styling.
 *
 * @param formData The current state of the form data.
 * @param onFormChange Callback invoked when form data changes.
 * @param onSubmit Callback invoked when the form is submitted.
 * @param modifier Optional [Modifier] for the form.
 * @param isLoading Whether the form is in a loading state.
 * @author Salim Ramirez
 */
@Composable
fun TransactionRegistrationForm(
    formData: TransactionFormData,
    onFormChange: (TransactionFormData) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
) {
    FormCard(
        title = stringResource(R.string.finance_register_title),
        description = stringResource(R.string.finance_register_description),
        modifier = modifier
    ) {
        // Type and Category fields
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = formData.type,
                onValueChange = { onFormChange(formData.copy(type = it)) },
                label = { Text(stringResource(R.string.finance_type)) },
                leadingIcon = {
                    Icon(Icons.Default.Payment, contentDescription = null)
                },
                modifier = Modifier.weight(1f),
                singleLine = true
            )

            OutlinedTextField(
                value = formData.category,
                onValueChange = { onFormChange(formData.copy(category = it)) },
                label = { Text(stringResource(R.string.finance_category)) },
                leadingIcon = {
                    Icon(Icons.Default.Category, contentDescription = null)
                },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
        }

        // Method field
        OutlinedTextField(
            value = formData.method,
            onValueChange = { onFormChange(formData.copy(method = it)) },
            label = { Text(stringResource(R.string.finance_method)) },
            leadingIcon = {
                Icon(Icons.Default.Payment, contentDescription = null)
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Amount and Currency fields
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = formData.amount,
                onValueChange = { onFormChange(formData.copy(amount = it)) },
                label = { Text(stringResource(R.string.finance_amount)) },
                leadingIcon = {
                    Icon(Icons.Default.AttachMoney, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.weight(1f),
                singleLine = true
            )

            OutlinedTextField(
                value = formData.currency,
                onValueChange = { onFormChange(formData.copy(currency = it)) },
                label = { Text(stringResource(R.string.finance_currency)) },
                modifier = Modifier.width(100.dp),
                singleLine = true
            )
        }

        // Date field
        OutlinedTextField(
            value = formData.date,
            onValueChange = { onFormChange(formData.copy(date = it)) },
            label = { Text(stringResource(R.string.finance_date)) },
            leadingIcon = {
                Icon(Icons.Default.CalendarToday, contentDescription = null)
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Description field
        OutlinedTextField(
            value = formData.description,
            onValueChange = { onFormChange(formData.copy(description = it)) },
            label = { Text(stringResource(R.string.finance_description)) },
            leadingIcon = {
                Icon(Icons.Default.Description, contentDescription = null)
            },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3,
            minLines = 2
        )

        // Submit button
        Button(
            onClick = onSubmit,
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Text(
                text = if (isLoading) {
                    stringResource(R.string.finance_register_button_loading)
                } else {
                    stringResource(R.string.finance_register_button)
                }
            )
        }
    }
}

