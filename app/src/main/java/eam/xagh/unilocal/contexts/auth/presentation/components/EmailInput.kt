package eam.xagh.unilocal.contexts.auth.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import eam.xagh.unilocal.R
import eam.xagh.unilocal.contexts.shared.presentation.components.Input

@Composable
fun EmailInput(
    email: String,
    isValid: Boolean,
    errorMessage: String?,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    onChange: (String) -> Unit
) {
    Input(
        value = email,
        label = stringResource(id = R.string.email),
        placeholder = stringResource(id = R.string.test_email),
        keyboardOptions = keyboardOptions,
        extraInvalidReason = !isValid,
        errorMessage = errorMessage
    ) { onChange(it) }
}