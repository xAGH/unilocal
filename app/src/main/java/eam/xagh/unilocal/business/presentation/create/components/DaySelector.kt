package eam.xagh.unilocal.business.presentation.create.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import eam.xagh.unilocal.R
import eam.xagh.unilocal.business.domain.constants.DayOfWeek
import eam.xagh.unilocal.core.presentation.utils.noRippleClickable
import eam.xagh.unilocal.shared.presentation.components.Text
import eam.xagh.unilocal.shared.presentation.theme.Shape

@Composable
fun DaySelector(
    exclude: List<DayOfWeek> = listOf(),
    selectedDay: DayOfWeek,
    showAll: Boolean,
    onDaySelectorClick: () -> Unit,
    onDaySelected: (DayOfWeek) -> Unit
) {
    val color = MaterialTheme.colorScheme
    Column(
        modifier = Modifier
            .clip(Shape.RoundedMedium)
            .noRippleClickable { onDaySelectorClick() }
    ) {
        if (showAll) {
            repeat(enumValues<DayOfWeek>().size) { index ->
                val day = enumValues<DayOfWeek>()[index]
                if (day !in exclude) {
                    Text(
                        text = day.value,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .background(if (day == selectedDay) color.primary else color.background)
                            .padding(vertical = 12.dp)
                            .width(110.dp)
                            .noRippleClickable {
                                onDaySelectorClick()
                                onDaySelected(day)
                            }
                    )
                }
            }
        } else {
            repeat(1) {
                Text(
                    text = selectedDay.value,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(color.background)
                        .padding(vertical = 12.dp)
                        .width(110.dp)
                )
            }
        }
    }
}