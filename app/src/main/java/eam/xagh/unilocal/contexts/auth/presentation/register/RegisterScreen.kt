package eam.xagh.unilocal.contexts.auth.presentation.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import eam.xagh.unilocal.R
import eam.xagh.unilocal.contexts.auth.presentation.AuthScreens
import eam.xagh.unilocal.contexts.auth.presentation.components.EmailInput
import eam.xagh.unilocal.contexts.auth.presentation.components.PasswordInput
import eam.xagh.unilocal.contexts.auth.presentation.register.components.CityInput
import eam.xagh.unilocal.contexts.auth.presentation.register.components.NameInput
import eam.xagh.unilocal.contexts.auth.presentation.register.components.NicknameInput
import eam.xagh.unilocal.contexts.shared.presentation.components.ClickableText
import eam.xagh.unilocal.contexts.shared.presentation.components.LargeButton
import eam.xagh.unilocal.contexts.shared.presentation.components.Title
import eam.xagh.unilocal.contexts.shared.presentation.components.Text
import eam.xagh.unilocal.contexts.shared.presentation.layouts.IllustrationLayout

@Composable
fun RegisterScreen(navController: NavController) {
    val viewModel: RegisterViewModel = hiltViewModel()
    val state = viewModel.state
    val registerIsValid = state.emailState.isValid &&
            state.nicknameState.isValid &&
            state.passwordState.isValid
    val nameValue = remember { mutableStateOf("") }
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val cityValue = remember { mutableStateOf("") }
    val nicknameValue = remember { mutableStateOf("") }
    val onDone = {
        viewModel.onEvent(
            RegisterEvent.RegisterUser(
                name = nameValue.value,
                email = emailValue.value,
                password = passwordValue.value,
                city = cityValue.value,
                nickname = nicknameValue.value
            )
        )
    }
    IllustrationLayout(illustrationName = "register") {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize()
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
                NameInput(viewModel, nameValue)
                EmailInput(
                    email = emailValue.value,
                    isValid = viewModel.state.emailState.isValid,
                    errorMessage = viewModel.state.emailState.error,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                ) {
                    emailValue.value = it
                    viewModel.onEvent(RegisterEvent.ValidateEmail(it))
                }
                PasswordInput(
                    password = passwordValue.value,
                    isVisible = viewModel.state.passwordState.isVisible,
                    isValid = viewModel.state.passwordState.isValid,
                    errorMessage = viewModel.state.passwordState.error,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next,
                        autoCorrect = false
                    ),
                    onPasswordChangeVisibility = { viewModel.onEvent(RegisterEvent.ChangePasswordVisibility) }
                ) {
                    passwordValue.value = it
                    viewModel.onEvent(RegisterEvent.ValidatePassword(it))
                }
                CityInput(viewModel, cityValue)
                NicknameInput(viewModel, nicknameValue) { onDone() }
                LargeButton(
                    text = stringResource(id = R.string.register),
                    enabled = registerIsValid,
                    modifier = Modifier.padding(top = 16.dp)
                ) { onDone() }

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(id = R.string.already_have_account))
                ClickableText(text = stringResource(id = R.string.login)) {
                    navController.navigate(AuthScreens.Login.route) { popUpTo(0) }
                }
            }
        }
    }
}