package eam.xagh.unilocal.contexts.auth.presentation.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import eam.xagh.unilocal.R
import eam.xagh.unilocal.contexts.shared.presentation.components.Input
import eam.xagh.unilocal.utils.noRippleClickable

@Composable
fun PasswordIcon(
    icon: ImageVector,
    onClick: () -> Unit
) {
    Icon(
        imageVector = icon,
        contentDescription = "PasswordIsVisible",
        modifier = Modifier.noRippleClickable { onClick() }
    )
}

@Composable
fun PasswordInput(
    password: String,
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    isValid: Boolean,
    errorMessage: String?,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions(),
    onPasswordChangeVisibility: () -> Unit,
    onChange: (String) -> Unit
) {
    val icon = if (isVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
    Input(
        value = password,
        label = stringResource(id = R.string.password),
        placeholder = stringResource(id = R.string.test_password),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        extraInvalidReason = !isValid,
        transformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        icon = { PasswordIcon(icon) { onPasswordChangeVisibility() } },
        errorMessage = errorMessage,
        modifier = modifier
    ) { onChange(it) }
}