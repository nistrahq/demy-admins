package com.nistra.demy.admins.features.auth.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nistra.demy.admins.R
import com.nistra.demy.admins.features.auth.presentation.ui.components.AuthFooter
import com.nistra.demy.admins.features.auth.presentation.ui.components.AuthHeader
import com.nistra.demy.admins.features.auth.presentation.ui.components.SignInForm

@Composable
fun SignInScreen(
    onLoggedIn: () -> Unit,
    onGoToSignUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthHeader(
            title = stringResource(R.string.sign_in_header_title),
            subtitle = stringResource(R.string.sign_in_header_subtitle)
        )

        Spacer(modifier = Modifier.height(32.dp))

        SignInForm(onLoggedIn = onLoggedIn)

        Spacer(modifier = Modifier.height(16.dp))

        AuthFooter(
            text = stringResource(R.string.sign_in_footer_text),
            actionText = stringResource(R.string.sign_in_footer_action_text),
            onActionClick = onGoToSignUp
        )
    }
}
