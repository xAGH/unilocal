package eam.xagh.unilocal.contexts.shared.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import eam.xagh.unilocal.R
import eam.xagh.unilocal.contexts.shared.presentation.theme.Shape

@Composable
fun Label(label: String, value: String, inputFocused: Boolean, modifier: Modifier = Modifier) {
    Text(
        label,
        style = if (value.isNotEmpty() || inputFocused) {
            MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Bold
            )
        } else {
            MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold
            )
        },
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Input(
    placeholder: String,
    modifier: Modifier = Modifier,
    label: String = "",
    extraInvalidReason: Boolean = true,
    value: String = "",
    errorMessage: String? = null,
    maxLines: Int = 1,
    hasFocus: Boolean = false,
    maxLength: Int = 50,
    readOnly: Boolean = false,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    singleLine: Boolean = true,
    icon: (@Composable () -> Unit)? = null,
    transformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onFocusRequest: () -> Unit = {},
    onFocusClear: () -> Unit = {},
    required: Boolean = true,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    onValueChange: (String) -> Unit = {}
) {
    val focusRequester = remember { FocusRequester() }
    var touched by remember { mutableStateOf(false) };
    val material = MaterialTheme.colorScheme
    val inputColors = TextFieldDefaults.textFieldColors(
        textColor = material.onSurface,
        placeholderColor = material.surfaceTint,
        containerColor = backgroundColor,
        unfocusedIndicatorColor = material.outline
    )
    var isInputFocused by remember { mutableStateOf(hasFocus) }
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        OutlinedTextField(
            value = value,
            shape = Shape.RoundedSmall,
            label = { Label(label, value, isInputFocused) },
            colors = inputColors,
            maxLines = maxLines,
            textStyle = textStyle,
            placeholder = {
                Text(
                    text = placeholder,
                    color = MaterialTheme.colorScheme.surfaceTint,
                    style = textStyle,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            onValueChange = {
                touched = true; if (it.length <= maxLength) onValueChange(it)
            },
            trailingIcon = icon,
            visualTransformation = transformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            readOnly = readOnly,
            isError = required && touched && extraInvalidReason,
            singleLine = singleLine,
            modifier = modifier
//                .align(CenterHorizontally)
                .height(MaterialTheme.typography.bodyMedium.fontSize.value.dp * 4)
                .focusRequester(focusRequester)
                .onFocusChanged {
                    isInputFocused = it.isFocused
                    if (isInputFocused) {
                        touched = true
                        onFocusRequest()
                    } else {
                        onFocusClear()
                    }
                }
        )
        if (required && touched && value.isEmpty()) {
            Text(
                text = stringResource(id = R.string.value_required),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}