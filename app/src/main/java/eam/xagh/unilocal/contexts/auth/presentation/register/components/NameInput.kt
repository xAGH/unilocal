package eam.xagh.unilocal.contexts.auth.presentation.register.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import eam.xagh.unilocal.R
import eam.xagh.unilocal.contexts.auth.presentation.register.RegisterEvent
import eam.xagh.unilocal.contexts.auth.presentation.register.RegisterViewModel
import eam.xagh.unilocal.contexts.shared.presentation.components.Input

@Composable
fun NameInput(viewModel: RegisterViewModel, name: MutableState<String>) {
    Input(
        label = stringResource(id = R.string.name),
        value = name.value,
        placeholder = stringResource(id = R.string.test_name),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Next,
            autoCorrect = true
        ),
        extraInvalidReason = !viewModel.state.nameState.isValid,
        errorMessage = viewModel.state.nameState.error
    ) { changedValue ->
        var transformed = changedValue.split(" ").joinToString(" ") {
            it.lowercase().replaceFirstChar { char -> char.uppercase() }
        }
        if (
            transformed.length >= 2
            && transformed.last().isWhitespace()
            && transformed[transformed.length - 2].isWhitespace()
        ) { transformed = transformed.trimEnd() }
        name.value = transformed
        viewModel.onEvent(RegisterEvent.ValidateName(changedValue))
    }
}