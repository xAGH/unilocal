package eam.xagh.unilocal.contexts.auth.presentation.register.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import eam.xagh.unilocal.R
import eam.xagh.unilocal.contexts.auth.presentation.register.RegisterEvent
import eam.xagh.unilocal.contexts.auth.presentation.register.RegisterViewModel
import eam.xagh.unilocal.contexts.shared.presentation.components.Select

@Composable
fun CityInput(viewModel: RegisterViewModel, city: MutableState<String>) {
    viewModel.onEvent(RegisterEvent.GetCities)
    Select(
        options = viewModel.state.cityState.cities,
        value = city,
        errorMessage = viewModel.state.cityState.error,
        label = stringResource(id = R.string.city),
        placeholder = stringResource(id = R.string.test_city)
    ) { city.value = it }
}