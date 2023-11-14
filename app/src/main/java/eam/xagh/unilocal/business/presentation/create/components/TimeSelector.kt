package eam.xagh.unilocal.business.presentation.create.components

import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import eam.xagh.unilocal.R
import eam.xagh.unilocal.core.presentation.utils.noRippleClickable
import eam.xagh.unilocal.shared.presentation.components.Text
import eam.xagh.unilocal.shared.presentation.theme.Shape
import java.util.Calendar

@Composable
fun TimeSelector(time: String, onTimeSelected: (time: String) -> Unit) {
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)
    val context = LocalContext.current
    val timePickerDialog = TimePickerDialog(
        context,
        { _, hourSelect, minSelect ->
            onTimeSelected("${format(hourSelect)}:${format(minSelect)}")
        }, hour, minute, true

    )
    Text(
        text = time,
        color = MaterialTheme.colorScheme.onPrimary,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center,
        modifier = androidx.compose.ui.Modifier
            .clip(Shape.RoundedLarge)
            .background(MaterialTheme.colorScheme.surface)
            .padding(vertical = 12.dp)
            .width(80.dp)
            .noRippleClickable { timePickerDialog.show() }
    )
}

private fun format(time: Int): String = time.toString().padStart(2, '0')