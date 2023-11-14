package eam.xagh.unilocal.shared.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import eam.xagh.unilocal.core.presentation.utils.noRippleClickable

@Composable
fun Select(
    options: List<String>,
    value: String,
    errorMessage: String = "",
    label: String = "",
    placeholder: String = "",
    isLoadingInfo: Boolean,
    withSearch: Boolean = true,
    onChange: (String) -> Unit = {},
    onDropDownIconClick: (String) -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }
    val valid = value in options

    Input(
        label = label,
        value = value,
        placeholder = placeholder,
        onFocusRequest = { showMenu = true },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        onFocusClear = { showMenu = false },
        extraInvalidReason = !valid,
        errorMessage = if (!valid && value.isNotEmpty()) errorMessage else null,
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
        onChange(it)
    }

    if (showMenu) {
        val filteredOptions = options.filter { option ->
            option.contains(value.trim(), ignoreCase = true)
        }

        LazyColumn(
            modifier = Modifier
                .heightIn(0.dp, 150.dp)
                .fillMaxWidth()
                .border(2.dp, Color.Black)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            if (isLoadingInfo) {
                items(1) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(50.dp)
                            .background(MaterialTheme.colorScheme.surface)
                    ) {
                        LoadingIcon(
                            animate = true, modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }
            } else {
                items(filteredOptions.size) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(if (filteredOptions[it] == value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface)
                            .noRippleClickable {
                                showMenu = false
                                onDropDownIconClick(filteredOptions[it])
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
}