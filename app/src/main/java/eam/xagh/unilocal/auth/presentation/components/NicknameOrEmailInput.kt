package eam.xagh.unilocal.auth.presentation.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import eam.xagh.unilocal.R
import eam.xagh.unilocal.shared.presentation.components.Input

@Composable
fun NicknameOrEmailInput(
    nicknameOrEmail: String,
    modifier: Modifier = Modifier,
    isValid: Boolean,
    errorMessage: String?,
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    readOnly: Boolean = false,
    onChange: (String) -> Unit
) {
    Input(
        value = nicknameOrEmail,
        modifier = modifier,
        label = stringResource(id = R.string.nickname_or_email),
        placeholder = "${stringResource(id = R.string.test_nickname)} / ${stringResource(id = R.string.test_email)}",
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        extraInvalidReason = !isValid,
        errorMessage = errorMessage,
        readOnly = readOnly
    ) { onChange(it) }
}