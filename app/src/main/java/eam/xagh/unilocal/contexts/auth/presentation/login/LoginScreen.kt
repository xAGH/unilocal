package eam.xagh.unilocal.contexts.auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import eam.xagh.unilocal.R
import eam.xagh.unilocal.contexts.auth.presentation.AuthScreens
import eam.xagh.unilocal.contexts.auth.presentation.components.NicknameOrEmailInput
import eam.xagh.unilocal.contexts.auth.presentation.components.PasswordInput
import eam.xagh.unilocal.contexts.shared.presentation.components.ClickableText
import eam.xagh.unilocal.contexts.shared.presentation.components.LargeButton
import eam.xagh.unilocal.contexts.shared.presentation.components.Text
import eam.xagh.unilocal.contexts.shared.presentation.components.Title
import eam.xagh.unilocal.contexts.shared.presentation.components.illustration.Illustration
import eam.xagh.unilocal.contexts.shared.presentation.layouts.IllustrationLayout
import eam.xagh.unilocal.presentation.base.BaseScreens
import eam.xagh.unilocal.utils.noRippleClickable

@Composable
fun LoginScreen(navController: NavController) {
    val viewModel: LoginViewModel = hiltViewModel()
    val nicknameOrEmailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val loginIsValid = viewModel.state.nicknameOrEmailState.isValid
            && viewModel.state.passwordState.isValid
    val onDone = {
        viewModel.onEvent(
            LoginEvent.Login(
                nicknameOrEmailValue.value,
                passwordValue.value
            )
        )
    }
    IllustrationLayout(illustrationName = "login", onReturnButtonClick = { navController.navigate(BaseScreens.Map.route) }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Title(
                body =stringResource(id = R.string.login_to),
                principal = stringResource(id = R.string.app_name),
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                NicknameOrEmailInput(
                    nicknameOrEmail = nicknameOrEmailValue.value,
                    isValid = viewModel.state.nicknameOrEmailState.isValid,
                    errorMessage = viewModel.state.nicknameOrEmailState.error,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                ) {
                    nicknameOrEmailValue.value = it
                    viewModel.onEvent(LoginEvent.ValidateNicknameOrEmail(it))
                }
                Column {
                    PasswordInput(
                        password = passwordValue.value,
                        isVisible = viewModel.state.passwordState.isVisible,
                        isValid = viewModel.state.passwordState.isValid,
                        errorMessage = viewModel.state.passwordState.error,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done,
                            autoCorrect = false
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { onDone() }
                        ),
                        onPasswordChangeVisibility = { viewModel.onEvent(LoginEvent.ChangePasswordVisibility) }
                    ) {
                        passwordValue.value = it
                        viewModel.onEvent(LoginEvent.ValidatePassword(it))
                    }
                    ClickableText(
                        text = stringResource(id = R.string.forget_password),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(top = 8.dp)
                    ) { navController.navigate(AuthScreens.PasswordRecovery.route) }
                }
                LargeButton(
                    text = stringResource(id = R.string.login),
                    enabled = loginIsValid,
                    modifier = Modifier.padding(top = 16.dp)
                ) { onDone() }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Divider(modifier = Modifier.weight(1f), color = MaterialTheme.colorScheme.onPrimary)
                Text(text = "OR")
                Divider(modifier = Modifier.weight(1f), color = MaterialTheme.colorScheme.onPrimary)
            }
            Box(
                Modifier
                    .align(CenterHorizontally)
                    .height(80.dp)
                    .width(80.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
            ) {
                Illustration(
                    illustrationName = "fingerprint",
                    modifier = Modifier
                        .fillMaxSize()
                        .noRippleClickable { /* TODO(Fingerprint auth) */ }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(id = R.string.dont_have_account))
                ClickableText(text = stringResource(id = R.string.register)) {
                    navController.navigate(AuthScreens.Register.route) { popUpTo(0) }
                }
            }
        }
    }
}