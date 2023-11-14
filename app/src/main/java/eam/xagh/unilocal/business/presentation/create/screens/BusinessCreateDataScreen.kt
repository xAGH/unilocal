package eam.xagh.unilocal.business.presentation.create.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import eam.xagh.unilocal.R
import eam.xagh.unilocal.business.presentation.BusinessScreens
import eam.xagh.unilocal.business.presentation.create.BusinessCreateViewModel
import eam.xagh.unilocal.business.presentation.create.BusinessCreateEvent
import eam.xagh.unilocal.business.presentation.create.components.SelectCategory
import eam.xagh.unilocal.shared.presentation.components.Input
import eam.xagh.unilocal.shared.presentation.components.LargeButton
import eam.xagh.unilocal.shared.presentation.components.Title
import eam.xagh.unilocal.shared.presentation.layouts.IllustrationLayout
import androidx.compose.ui.text.input.ImeAction

@Composable
fun BusinessCreateDataScreen(navController: NavController) {
    val viewModel: BusinessCreateViewModel = hiltViewModel()
    val state = viewModel.state
    val businessNameValue = remember { mutableStateOf(state.businessNameState.value) }
    val businessDescriptionValue = remember { mutableStateOf(state.businessDescriptionState.value) }
    val selectedCategory = remember { mutableStateOf(state.businessCategory) }
    val businessDataIsValid =
        state.businessNameState.isValid && state.businessDescriptionState.isValid && selectedCategory.value.isNotEmpty()
    val onDone = {
        val route = BusinessScreens.CreateContact.route
        navController.navigate(route) {
            popUpTo(route) { inclusive = false }
        }
    }

    IllustrationLayout(
        illustrationName = "create_business_data",
        onReturnButtonClick = { navController.popBackStack() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Title(
                body = stringResource(id = R.string.create_business),
                principal = stringResource(id = R.string.data),
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Input(
                    value = businessNameValue.value,
                    label = stringResource(id = R.string.business_name),
                    placeholder = stringResource(id = R.string.business_name_placeholder),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next,
                        autoCorrect = true
                    ),
                    extraInvalidReason = !state.businessNameState.isValid,
                    errorMessage = state.businessNameState.error,
                ) {
                    businessNameValue.value = it
                    viewModel.onEvent(BusinessCreateEvent.ValidateBusinessName(it))
                }
                SelectCategory(selectedCategory.value, viewModel.businessCategories) {
                    selectedCategory.value = it
                    viewModel.onEvent(BusinessCreateEvent.SaveCategory(it))
                }
                Input(value = businessDescriptionValue.value,
                    label = stringResource(id = R.string.business_description),
                    placeholder = stringResource(id = R.string.business_description_placeholder),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.None,
                        autoCorrect = true
                    ),
                    maxLength = Int.MAX_VALUE,
                    singleLine = false,
                    keyboardActions = KeyboardActions(
                        onAny = { businessDescriptionValue.value += "\r\n" }
                    ),
                    extraInvalidReason = !state.businessDescriptionState.isValid,
                    errorMessage = state.businessDescriptionState.error,
                    maxLines = 2) {
                    businessDescriptionValue.value = it
                    viewModel.onEvent(BusinessCreateEvent.ValidateBusinessDescription(it))
                }
                LargeButton(
                    text = stringResource(id = R.string.continue_text),
                    enabled = businessDataIsValid,
                    modifier = Modifier.padding(top = 16.dp)
                ) { onDone() }
            }
        }
    }
}