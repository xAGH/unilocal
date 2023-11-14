package eam.xagh.unilocal.auth.presentation.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import eam.xagh.unilocal.R
import eam.xagh.unilocal.shared.presentation.components.Input
import eam.xagh.unilocal.shared.presentation.components.LoadingIcon

@Composable
fun NicknameInput(
    value: String,
    isValid: Boolean,
    icon: ImageVector?,
    errorMessage: String?,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions(),
    onChange: (String) -> Unit
) {
    Input(
        value = value,
        label = stringResource(id = R.string.nickname),
        placeholder = stringResource(id = R.string.test_nickname),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        extraInvalidReason = if (icon == null) true else !isValid,
        icon = {
            if (!(value.isEmpty() || value.isBlank())) {
                LoadingIcon(
                    animate = icon == null,
                    color = if (isValid && icon != null) MaterialTheme.colorScheme.tertiary else LocalContentColor.current,
                    imageVector = icon
                )
            }
        },
        errorMessage = errorMessage
    ) {
        onChange(it)
    }
}