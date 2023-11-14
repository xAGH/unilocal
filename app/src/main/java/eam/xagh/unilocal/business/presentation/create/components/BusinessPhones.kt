package eam.xagh.unilocal.business.presentation.create.components

import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import eam.xagh.unilocal.R
import eam.xagh.unilocal.core.presentation.utils.noRippleClickable
import eam.xagh.unilocal.shared.presentation.components.Text

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BusinessPhones(phones: List<String>, onPhonesChanged: (List<String>) -> Unit) {
    Text(
        text = stringResource(id = R.string.phones),
        style = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.padding(bottom = 8.dp)
    )
    FlowRow(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        repeat(phones.size) { index ->
            PhoneInput(
                value = phones[index],
            ) { phoneNewValue ->
                val updateList = phones.toMutableList()
                updateList[index] = phoneNewValue
                onPhonesChanged(updateList.toList())
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {

        }
        if (phones.size < 6) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(MaterialTheme.typography.bodyMedium.fontSize.value.dp * 2)
                    .noRippleClickable {
                        onPhonesChanged(phones.plus(""))
                    },
            )
        }

        if (phones.size > 1) {
            Icon(
                imageVector = Icons.Filled.Remove,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .size(MaterialTheme.typography.bodyMedium.fontSize.value.dp * 2)
                    .noRippleClickable {

                        onPhonesChanged(phones.dropLast(1))

                    },
            )

        }
    }
}