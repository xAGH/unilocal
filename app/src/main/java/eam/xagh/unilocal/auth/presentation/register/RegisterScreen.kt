package eam.xagh.unilocal.auth.presentation.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import eam.xagh.unilocal.R
import eam.xagh.unilocal.auth.domain.values.RegisterResponse
import eam.xagh.unilocal.auth.presentation.AuthScreens
import eam.xagh.unilocal.auth.presentation.components.EmailInput
import eam.xagh.unilocal.auth.presentation.components.NameInput
import eam.xagh.unilocal.auth.presentation.components.PasswordInput
import eam.xagh.unilocal.auth.presentation.components.NicknameInput
import eam.xagh.unilocal.core.presentation.base.BaseScreens
import eam.xagh.unilocal.shared.presentation.components.ClickableText
import eam.xagh.unilocal.shared.presentation.components.LargeButton
import eam.xagh.unilocal.shared.presentation.components.Select
import eam.xagh.unilocal.shared.presentation.components.Title
import eam.xagh.unilocal.shared.presentation.components.Text
import eam.xagh.unilocal.shared.presentation.layouts.IllustrationLayout

@Composable
fun RegisterScreen(navController: NavController) {
    val viewModel: RegisterViewModel = hiltViewModel()
    val state = viewModel.state
    val options = viewModel.state.cityState.cities.map { it.name }
    val registerIsValid =
        state.emailState.isValid && state.nicknameState.isValid && state.passwordState.isValid
    val nameValue = remember { mutableStateOf("") }
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val cityValue = remember { mutableStateOf("") }
    val nicknameValue = remember { mutableStateOf("") }
    val registerStatus = remember { mutableStateOf(RegisterResponse(true, null)) }
    val onDone = {
        viewModel.onEvent(
            RegisterEvent.RegisterUser(
                name = nameValue.value,
                email = emailValue.value,
                password = passwordValue.value,
                city = viewModel.state.cityState.cities.first { it.name == cityValue.value },
                nickname = nicknameValue.value
            ) { response ->
                if (response.success)
                    navController.navigate(AuthScreens.Login.route) {
                        popUpTo(BaseScreens.Map.route) { inclusive = true }
                    }
                else
                    registerStatus.value = response
            }
        )
    }

    if (!registerStatus.value.success) {
        AlertDialog(
            icon = { Icon(Icons.Filled.Info, null) },
            title = {
                Text(
                    text = stringResource(id = R.string.register_failed),
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            text = {
                Text(
                    text = registerStatus.value.errorMessage
                        ?: stringResource(id = R.string.unexpected_error)
                )
            },
            onDismissRequest = { registerStatus.value = RegisterResponse(true, null) },
            confirmButton = {},
        )
    }

    if (viewModel.state.doingRegister) {
        Dialog(
            onDismissRequest = {},
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }

    IllustrationLayout(
        illustrationName = "register",
        onReturnButtonClick = {
            navController.popBackStack()
        }
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxSize()
        ) {
            Title(
                body = stringResource(id = R.string.register_into),
                principal = stringResource(id = R.string.app_name)
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.8f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                NameInput(
                    name = nameValue,
                    isValid = viewModel.state.nameState.isValid,
                    errorMessage = viewModel.state.nameState.error,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next,
                        autoCorrect = true
                    )
                ) {
                    nameValue.value = it
                    viewModel.onEvent(RegisterEvent.ValidateName(it))
                }
                EmailInput(
                    email = emailValue.value,
                    isValid = viewModel.state.emailState.isValid,
                    errorMessage = viewModel.state.emailState.error,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                    )
                ) {
                    emailValue.value = it
                    viewModel.onEvent(RegisterEvent.ValidateEmail(it))
                }
                PasswordInput(password = passwordValue.value,
                    isVisible = viewModel.state.passwordState.isVisible,
                    isValid = viewModel.state.passwordState.isValid,
                    errorMessage = viewModel.state.passwordState.error,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next,
                        autoCorrect = false
                    ),
                    onPasswordChangeVisibility = { viewModel.onEvent(RegisterEvent.ChangePasswordVisibility) }) {
                    passwordValue.value = it
                    viewModel.onEvent(RegisterEvent.ValidatePassword(it))
                }

                Select(
                    options = options,
                    value = cityValue.value,
                    errorMessage = stringResource(id = R.string.city_error),
                    label = stringResource(id = R.string.city),
                    placeholder = stringResource(id = R.string.test_city),
                    isLoadingInfo = viewModel.state.cityState.isLoading,
                    onChange = {
                        cityValue.value = it
                    }
                ) { cityValue.value = it }
                NicknameInput(
                    value = nicknameValue.value,
                    isValid = viewModel.state.nicknameState.isValid,
                    icon = viewModel.state.nicknameState.icon,
                    errorMessage = viewModel.state.nicknameState.error,
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { onDone() }
                    ),
                ) {
                    viewModel.onEvent(RegisterEvent.ValidateNickname(it))
                    nicknameValue.value = it
                }
                LargeButton(
                    text = stringResource(id = R.string.register),
                    enabled = registerIsValid,
                    modifier = Modifier.padding(top = 16.dp)
                ) { onDone() }

            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(id = R.string.already_have_account))
                ClickableText(text = stringResource(id = R.string.login)) {
                    navController.navigate(AuthScreens.Login.route)
                }
            }
        }
    }
}