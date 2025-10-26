package com.nistra.demy.admins.core.common

/**
 * Represents a message to be displayed in a Snackbar.
 *
 * This data class encapsulates all information needed to display a semantic
 * Snackbar message with appropriate styling based on the message type.
 *
 * This class belongs to the common/presentation layer, not the UI layer,
 * allowing ViewModels to create snackbar messages without depending on UI components.
 *
 * @property message The text content to display in the Snackbar.
 * @property type The semantic type of the message (SUCCESS, ERROR, WARNING, INFO).
 * @property actionLabel Optional label for an action button.
 * @property onActionClick Optional callback invoked when the action button is clicked.
 *
 * @author Salim Ramirez
 */
data class SnackbarMessage(
    val message: String,
    val type: SnackbarType = SnackbarType.INFO,
    val actionLabel: String? = null,
    val onActionClick: (() -> Unit)? = null
)

