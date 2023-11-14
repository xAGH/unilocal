package eam.xagh.unilocal.auth.presentation.login

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import eam.xagh.unilocal.R
import eam.xagh.unilocal.auth.presentation.AuthScreens
import eam.xagh.unilocal.auth.presentation.components.NicknameOrEmailInput
import eam.xagh.unilocal.auth.presentation.components.PasswordInput
import eam.xagh.unilocal.shared.presentation.components.ClickableText
import eam.xagh.unilocal.shared.presentation.components.LargeButton
import eam.xagh.unilocal.shared.presentation.components.Text
import eam.xagh.unilocal.shared.presentation.components.Title
import eam.xagh.unilocal.shared.presentation.components.illustration.Illustration
import eam.xagh.unilocal.shared.presentation.layouts.IllustrationLayout
import eam.xagh.unilocal.core.presentation.base.BaseScreens
import eam.xagh.unilocal.core.presentation.utils.noRippleClickable
import eam.xagh.unilocal.shared.presentation.authentication.AuthenticationViewModel

const val BIOMETRIC_AUTHENTICATORS =
    BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL

private fun authenticateByBiometricMethod(
    context: Context, onFinish: (success: Boolean) -> Unit
) {
    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle(context.resources.getString(R.string.authenticate_biometric_title))
        .setSubtitle(context.resources.getString(R.string.authenticate_biometric_subtitle))
        .setAllowedAuthenticators(BIOMETRIC_AUTHENTICATORS).build()

    BiometricPrompt(context as AppCompatActivity,
        ContextCompat.getMainExecutor(context),
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                onFinish(true)
            }
        }).authenticate(promptInfo)
}

private fun canAuthenticateByBiometricMethod(context: Context): Boolean =
    BiometricManager.from(context)
        .canAuthenticate(BIOMETRIC_AUTHENTICATORS) == BiometricManager.BIOMETRIC_SUCCESS


@Composable
fun LoginScreen(navController: NavController) {
    val viewModel: LoginViewModel = hiltViewModel()
    val authViewModel: AuthenticationViewModel = hiltViewModel()
    val nicknameOrEmailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val loginFailed = remember { mutableStateOf(false) }
    val cantAuthenticateBiometrically = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val loginIsValid =
        viewModel.state.nicknameOrEmailState.isValid && viewModel.state.passwordState.isValid

    fun onDone(byBiometric: Boolean = false) {
        viewModel.onEvent(LoginEvent.Login(
            nicknameOrEmailValue.value, passwordValue.value, byBiometric
        ) { loginSuccess ->
            if (loginSuccess) navController.navigate(BaseScreens.Map.route) {
                popUpTo(BaseScreens.Map.route) { inclusive = true }
            }
            else loginFailed.value = true
        })
    }

    if (loginFailed.value) {
        AlertDialog(
            icon = { Icon(Icons.Filled.Info, null) },
            title = {
                Text(
                    text = stringResource(id = R.string.login_failed),
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            text = { Text(text = stringResource(id = R.string.invalid_credentials)) },
            onDismissRequest = { loginFailed.value = false },
            confirmButton = {},
        )
    }

    if (viewModel.state.doingLogin) {
        Dialog(
            onDismissRequest = {},
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }

    if (cantAuthenticateBiometrically.value) {
        AlertDialog(
            icon = { Icon(Icons.Filled.Info, null) },
            title = {
                Text(
                    text = stringResource(id = R.string.cant_biometric_auth),
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            text = { Text(text = stringResource(id = R.string.login_first)) },
            onDismissRequest = { cantAuthenticateBiometrically.value = false },
            confirmButton = {},
        )
    }

    IllustrationLayout(
        illustrationName = "login",
        onReturnButtonClick = { navController.popBackStack() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Title(
                body = stringResource(id = R.string.login_to),
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
                        keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                    )
                ) {
                    nicknameOrEmailValue.value = it
                    viewModel.onEvent(LoginEvent.ValidateNicknameOrEmail(it))
                }
                Column {
                    PasswordInput(password = passwordValue.value,
                        isVisible = viewModel.state.passwordState.isVisible,
                        isValid = viewModel.state.passwordState.isValid,
                        errorMessage = viewModel.state.passwordState.error,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done,
                            autoCorrect = false
                        ),
                        keyboardActions = KeyboardActions(onDone = { onDone() }),
                        onPasswordChangeVisibility = { viewModel.onEvent(LoginEvent.ChangePasswordVisibility) }) {
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
                Divider(
                    modifier = Modifier.weight(1f), color = MaterialTheme.colorScheme.onPrimary
                )
                Text(text = "OR")
                Divider(
                    modifier = Modifier.weight(1f), color = MaterialTheme.colorScheme.onPrimary
                )
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
                Illustration(illustrationName = "fingerprint",
                    modifier = Modifier
                        .fillMaxSize()
                        .noRippleClickable {
                            if (canAuthenticateByBiometricMethod(context) && authViewModel.state.user != null) {
                                authenticateByBiometricMethod(context) { onDone(true) }
                            } else cantAuthenticateBiometrically.value = true
                        })
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(id = R.string.dont_have_account))
                ClickableText(text = stringResource(id = R.string.register)) {
                    navController.navigate(AuthScreens.Register.route)
                }
            }

        }
    }
}