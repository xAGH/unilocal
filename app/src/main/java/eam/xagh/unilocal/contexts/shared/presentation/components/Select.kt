package eam.xagh.unilocal.contexts.shared.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import eam.xagh.unilocal.utils.noRippleClickable

@Composable
fun Select(
    options: List<String>,
    value: MutableState<String>,
    errorMessage: String,
    label: String,
    placeholder: String,
    onOptionSelected: (String) -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }
    var filteredOptions by remember { mutableStateOf(options) }
    val valid = value.value in options
    Input(
        label = label,
        value = value.value,
        placeholder = placeholder,
        onFocusRequest = { showMenu = true },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        onFocusClear = { showMenu = false },
        extraInvalidReason = !valid,
        errorMessage = if (!valid && value.value.isNotEmpty()) errorMessage else null,
        icon = {
            Icon(
                imageVector = if (showMenu) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier.noRippleClickable {
                    showMenu = !showMenu
                }
            )
        },
    ) {
        filteredOptions = options.filter { option ->
            option.contains(it.trim(), ignoreCase = true)
        }
        value.value = it
    }

    if (showMenu) {
        LazyColumn(
            modifier = Modifier
                .heightIn(0.dp, 150.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            items(filteredOptions.size) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(if (filteredOptions[it] == value.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface)
                        .noRippleClickable {
                            showMenu = false
                            value.value = filteredOptions[it]
                            onOptionSelected(filteredOptions[it])
                        }
                ) {
                    Text(
                        filteredOptions[it],
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}