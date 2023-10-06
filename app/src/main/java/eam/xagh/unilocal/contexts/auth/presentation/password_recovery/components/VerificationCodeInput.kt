package eam.xagh.unilocal.contexts.auth.presentation.password_recovery.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import eam.xagh.unilocal.R
import eam.xagh.unilocal.contexts.shared.presentation.components.Input

@Composable
fun VerificationCodeInput(
    code: String,
    modifier: Modifier = Modifier,
    isValid: Boolean,
    errorMessage: String?,
    codeLength: Int,
    readOnly: Boolean,
    onDone: () -> Unit,
    onChange: (String) -> Unit,
) {
    var placeholder = ""
    repeat(codeLength) { placeholder += "X" }
    Input(
        value = code,
        maxLength = codeLength,
        label = stringResource(id = R.string.verication_code),
        placeholder = placeholder.trimEnd(),
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            textAlign = TextAlign.Center,
            letterSpacing = 16.sp
        ),
        readOnly = readOnly,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { onDone() }
        ),
        extraInvalidReason = !isValid,
        errorMessage = errorMessage,
        modifier = modifier
    ) { onChange(it) }
}