package eam.xagh.unilocal.business.presentation.create.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import eam.xagh.unilocal.R
import eam.xagh.unilocal.business.domain.constants.DayOfWeek
import eam.xagh.unilocal.business.domain.entities.OpenDay
import eam.xagh.unilocal.business.presentation.BusinessScreens
import eam.xagh.unilocal.business.presentation.create.BusinessCreateEvent
import eam.xagh.unilocal.business.presentation.create.BusinessCreateViewModel
import eam.xagh.unilocal.business.presentation.create.components.DaySelector
import eam.xagh.unilocal.business.presentation.create.components.TimeSelector
import eam.xagh.unilocal.shared.presentation.components.LargeButton
import eam.xagh.unilocal.shared.presentation.components.Text
import eam.xagh.unilocal.shared.presentation.components.Title
import eam.xagh.unilocal.shared.presentation.layouts.IllustrationLayout

@Composable
private fun NoSchedules() {
    Text(
        text = stringResource(id = R.string.no_schedule),
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun Schedules(
    openDay: OpenDay,
    usedDays: List<DayOfWeek>,
    onDaySelected: (DayOfWeek) -> Unit,
    onUntilTimeSelected: (String) -> Unit,
    onFromTimeSelected: (String) -> Unit
) {
    val showAll = remember { mutableStateOf(false) }
    val selectedDay: MutableState<DayOfWeek> = remember { mutableStateOf(openDay.day) }
    val fromTime = remember { mutableStateOf(openDay.from) }
    val untilTime = remember { mutableStateOf(openDay.from) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DaySelector(showAll = showAll.value,
            selectedDay = selectedDay.value,
            exclude = usedDays,
            onDaySelectorClick = { showAll.value = !showAll.value }) {
            selectedDay.value = it
            onDaySelected(it)
        }
        TimeSelector(untilTime.value) {
            untilTime.value = it
            onUntilTimeSelected(it)
        }
        Text(text = stringResource(id = R.string.to))
        TimeSelector(fromTime.value) {
            fromTime.value = it
            onFromTimeSelected(it)
        }
    }
}

@Composable
fun BusinessCreateScheduleScreen(navController: NavController) {
    val viewModel: BusinessCreateViewModel = hiltViewModel()
    val openDays: MutableState<List<OpenDay>> =
        remember { mutableStateOf(viewModel.state.businessSchedule) }
    val usedDays: MutableState<List<DayOfWeek>> = remember {
        mutableStateOf(openDays.value.map { it.day })
    }

    if (viewModel.state.creatingBusiness) {
        Dialog(
            onDismissRequest = {},
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }

    IllustrationLayout(illustrationName = "create_business_schedule",
        onReturnButtonClick = { navController.popBackStack() }) {
        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween
        ) {
            Title(
                body = stringResource(id = R.string.create_business),
                principal = stringResource(id = R.string.schedule),
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(vertical = 8.dp)
                ) {
                    if (openDays.value.isEmpty()) NoSchedules()
                    else {
                        repeat(openDays.value.size) { index ->
                            Schedules(openDay = openDays.value[index],
                                usedDays = usedDays.value,
                                onDaySelected = { newDay ->
                                    val updateOpenDays = openDays.value.toMutableList()
                                    updateOpenDays[index] = updateOpenDays[index].copy(day = newDay)
                                    openDays.value = updateOpenDays.toList()
                                },
                                onFromTimeSelected = { fromTime ->
                                    val updateOpenDays = openDays.value.toMutableList()
                                    updateOpenDays[index] =
                                        updateOpenDays[index].copy(from = fromTime)
                                    openDays.value = updateOpenDays.toList()
                                },
                                onUntilTimeSelected = { untilTime ->
                                    val updateOpenDays = openDays.value.toMutableList()
                                    updateOpenDays[index] =
                                        updateOpenDays[index].copy(until = untilTime)
                                    openDays.value = updateOpenDays.toList()
                                })
                        }
                    }
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                FloatingActionButton(
                    onClick = {
                        val canAddMore = openDays.value.all {
                            it.day != DayOfWeek.NONE && it.from != "--:--" && it.until != "--:--"
                        }
                        if (usedDays.value.size != DayOfWeek.values().size - 1 && canAddMore) {
                            openDays.value =
                                openDays.value.plus(OpenDay(DayOfWeek.NONE, "--:--", "--:--"))
                            usedDays.value = openDays.value.map { it.day }
                        }
                    },
                    shape = CircleShape,
                    containerColor = MaterialTheme.colorScheme.background,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Icon(
                        Icons.Filled.Add,
                        "Large floating action button",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            LargeButton(
                text = stringResource(id = R.string.create_business),
                modifier = Modifier.padding(top = 16.dp),
                enabled = openDays.value.all {
                    it.day != DayOfWeek.NONE && it.from != "--:--" && it.until != "--:--"
                } && openDays.value.isNotEmpty()
            ) {
                viewModel.onEvent(BusinessCreateEvent.SaveSchedules(openDays.value))
                viewModel.onEvent(BusinessCreateEvent.CreateBusiness {
                    val route = BusinessScreens.List.route
                    navController.navigate(route) {
                        popUpTo(route) { inclusive = true }
                    }
                })
            }
        }
    }
}