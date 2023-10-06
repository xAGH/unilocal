package eam.xagh.unilocal.contexts.auth.presentation.password_recovery

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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import eam.xagh.unilocal.R
import eam.xagh.unilocal.contexts.auth.presentation.components.NicknameOrEmailInput
import eam.xagh.unilocal.contexts.auth.presentation.components.PasswordInput
import eam.xagh.unilocal.contexts.auth.presentation.password_recovery.components.VerificationCodeInput
import eam.xagh.unilocal.contexts.shared.presentation.components.LargeButton
import eam.xagh.unilocal.contexts.shared.presentation.components.Title
import eam.xagh.unilocal.contexts.shared.presentation.layouts.IllustrationLayout

@Composable
fun PasswordRecoveryScreen() {
    val viewModel: PasswordRecoveryViewModel = hiltViewModel()
    val nicknameOrEmailValue = remember { mutableStateOf("") }
    val newPasswordValue = remember { mutableStateOf("") }
    val codeValue = remember { mutableStateOf("") }
    var canRecovery = viewModel.state.nicknameOrEmailState.isValid
    var onDone = {
        viewModel.onEvent(
            PasswordRecoveryEvent.SendRecoveryCode(
                nicknameOrEmailValue.value
            )
        )
    }
    val sent = viewModel.state.recoveryCodeState.isSent
    val match = viewModel.state.recoveryCodeState.match
    val sendRecoveryCodeText = stringResource(id = R.string.send_recover_code)
    val verifyCodeText = stringResource(id = R.string.verify_code)
    val resetPasswordText = stringResource(id = R.string.reset_password)
    val buttonText = if (match) resetPasswordText
    else if (sent) verifyCodeText
    else sendRecoveryCodeText
    IllustrationLayout(
        illustrationName = "forgot_password",
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            val space = 16.dp
            Title(
                body = stringResource(id = R.string.recover_my),
                principal = stringResource(id = R.string.password)
            )
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                NicknameOrEmailInput(
                    nicknameOrEmail = nicknameOrEmailValue.value,
                    isValid = viewModel.state.nicknameOrEmailState.isValid,
                    errorMessage = viewModel.state.nicknameOrEmailState.error,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email, imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { onDone() }),
                    readOnly = viewModel.state.recoveryCodeState.isSent,
                    modifier = Modifier.padding(top = space)
                ) {
                    nicknameOrEmailValue.value = it
                    viewModel.onEvent(PasswordRecoveryEvent.ValidateNicknameOrEmail(it))
                }
                if (sent) {
                    val codeLength = 6
                    val codeIsValid = codeValue.value.length == codeLength
                    canRecovery = codeIsValid
                    onDone = {
                        viewModel.onEvent(
                            PasswordRecoveryEvent.ValidateRecoveryCode(
                                codeValue.value, nicknameOrEmailValue.value
                            )
                        )
                    }
                    VerificationCodeInput(code = codeValue.value,
                        isValid = codeIsValid,
                        readOnly = viewModel.state.recoveryCodeState.match,
                        codeLength = codeLength,
                        errorMessage = viewModel.state.recoveryCodeState.error,
                        onDone = onDone,
                        modifier = Modifier.padding(top = space),
                        onChange = {
                            codeValue.value = it.trim()
                        })
                }

                if (match) {
                    canRecovery = viewModel.state.newPasswordState.isValid
                    onDone = {
                        viewModel.onEvent(
                            PasswordRecoveryEvent.RecoveryPassword(
                                nicknameOrEmailValue.value, newPasswordValue.value
                            )
                        )
                    }
                    PasswordInput(password = newPasswordValue.value,
                        isVisible = viewModel.state.newPasswordState.isVisible,
                        isValid = viewModel.state.newPasswordState.isValid,
                        errorMessage = viewModel.state.newPasswordState.error,
                        modifier = Modifier.padding(top = space),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done,
                            autoCorrect = false
                        ),
                        keyboardActions = KeyboardActions(onDone = { onDone() }),
                        onPasswordChangeVisibility = { viewModel.onEvent(PasswordRecoveryEvent.ChangePasswordVisibility) }) {
                        newPasswordValue.value = it
                        viewModel.onEvent(PasswordRecoveryEvent.ValidatePassword(it))
                    }
                }
                LargeButton(
                    text = buttonText,
                    enabled = canRecovery,
                    modifier = Modifier.padding(top = space * 2)
                ) { onDone() }
            }
        }
    }
}