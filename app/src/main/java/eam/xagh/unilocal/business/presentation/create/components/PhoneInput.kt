package eam.xagh.unilocal.business.presentation.create.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import eam.xagh.unilocal.R
import eam.xagh.unilocal.shared.presentation.components.Text
import eam.xagh.unilocal.shared.presentation.theme.Shape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneInput(
    value: String,
    onPhoneWrite: (value: String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    var text by remember { mutableStateOf(value) }
    val interactionSource = remember { MutableInteractionSource() }
    val materialColors = MaterialTheme.colorScheme
    Column {
        BasicTextField(
            value = text,
            modifier = Modifier
                .height((MaterialTheme.typography.bodyMedium.fontSize.value * 3).dp)
                .padding(bottom = 16.dp)
                .fillMaxWidth(0.35f),
            onValueChange = {
                if (it.length <= 10) text = it
                onPhoneWrite(it)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            interactionSource = interactionSource,
            singleLine = true,
            decorationBox = @Composable { innerTextField ->
                TextFieldDefaults.TextFieldDecorationBox(
                    contentPadding = PaddingValues(10.dp, 0.dp),
                    value = text,
                    visualTransformation = VisualTransformation.None,
                    innerTextField = innerTextField,
                    placeholder = {
                        Text(
                            text = "555 555 55 55",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.surfaceTint,
                            modifier = Modifier
                                .padding(0.dp)
                                .height(MaterialTheme.typography.bodyMedium.fontSize.value.dp)
                        )
                    },
                    shape = Shape.RoundedLarge,
                    singleLine = true,
                    enabled = true,
                    isError = false,
                    interactionSource = interactionSource,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = materialColors.onSurface,
                        disabledTextColor = Color.Transparent,
                        containerColor = materialColors.surface,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
            }
        )
    }
}