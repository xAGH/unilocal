package eam.xagh.unilocal.auth.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import eam.xagh.unilocal.R
import eam.xagh.unilocal.shared.presentation.components.Input

@Composable
fun NameInput(
    name: MutableState<String>,
    isValid: Boolean,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    errorMessage: String?,
    onChange: (String) -> Unit
) {
    Input(
        label = stringResource(id = R.string.name),
        value = name.value,
        placeholder = stringResource(id = R.string.test_name),
        keyboardOptions = keyboardOptions,
        extraInvalidReason = !isValid,
        errorMessage = errorMessage
    ) { changedValue ->
        var transformed = changedValue.split(" ").joinToString(" ") {
            it.lowercase().replaceFirstChar { char -> char.uppercase() }
        }
        if (
            transformed.length >= 2
            && transformed.last().isWhitespace()
            && transformed[transformed.length - 2].isWhitespace()
        ) {
            transformed = transformed.trimEnd()
        }
        onChange(transformed)
    }
}