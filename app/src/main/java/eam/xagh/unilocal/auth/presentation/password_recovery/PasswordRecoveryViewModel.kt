package eam.xagh.unilocal.auth.presentation.password_recovery

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eam.xagh.unilocal.R
import eam.xagh.unilocal.auth.domain.repositories.UserRepository
import eam.xagh.unilocal.auth.domain.usecases.user.RecoveryPassword
import eam.xagh.unilocal.auth.domain.usecases.user.SendRecoveryCode
import eam.xagh.unilocal.auth.domain.usecases.validate.ValidateEmail
import eam.xagh.unilocal.auth.domain.usecases.validate.ValidateNickname
import eam.xagh.unilocal.auth.domain.usecases.validate.ValidatePassword
import eam.xagh.unilocal.auth.domain.usecases.validate.ValidateRecoveryCode
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordRecoveryViewModel @Inject constructor(
    private val recoveryPassword: RecoveryPassword,
    private val sendRecoveryCode: SendRecoveryCode,
    private val validateEmail: ValidateEmail,
    private val validateNickname: ValidateNickname,
    private val userRepository: UserRepository,
    private val validatePassword: ValidatePassword,
    private val validateRecoveryCode: ValidateRecoveryCode,
    private val application: Application
) : ViewModel() {
    var state by mutableStateOf(PasswordRecoveryState())
        private set

    private fun error(id: Int): String = application.resources.getString(id)

    private fun isEmail(value: String): Boolean = value.contains("@")

    fun onEvent(event: PasswordRecoveryEvent) {
        when (event) {
            is PasswordRecoveryEvent.RecoveryPassword -> {
                viewModelScope.launch {
                    recoveryPassword(
                        event.nicknameOrEmail,
                        event.newPassword,
                        userRepository
                    )
                }
                Toast.makeText(application.applicationContext, "Ok", Toast.LENGTH_SHORT).show()
            }

            is PasswordRecoveryEvent.SendRecoveryCode -> {
                viewModelScope.launch {
                    val valid = sendRecoveryCode(event.nicknameOrEmail, userRepository)

                    if (valid) {
                        state = state.copy(
                            recoveryCodeState = state.recoveryCodeState.copy(
                                isSent = true
                            )
                        )
                    } else {
                        state = state.copy(
                            recoveryCodeState = state.recoveryCodeState.copy(
                                isSent = false,
                            ),
                            nicknameOrEmailState = state.nicknameOrEmailState.copy(
                                error = error(if (isEmail(event.nicknameOrEmail)) R.string.email_not_found else R.string.nickname_not_found)
                            )
                        )
                    }
                }
            }

            is PasswordRecoveryEvent.ValidateNicknameOrEmail -> {
                viewModelScope.launch {
                    val value = event.nicknameOrEmail
                    val isValid =
                        if (value.contains("@")) validateEmail(value) else validateNickname(value)
                    state = state.copy(
                        nicknameOrEmailState = state.nicknameOrEmailState.copy(
                            isValid = isValid,
                            error = if (!isValid) {
                                if (isEmail(event.nicknameOrEmail)) error(R.string.email_error)
                                else error(R.string.nickname_error)
                            } else null
                        ),
                    )
                }
            }

            is PasswordRecoveryEvent.ValidatePassword -> {
                viewModelScope.launch {
                    val isValid =
                        validatePassword(event.password)
                    state = state.copy(
                        newPasswordState = state.newPasswordState.copy(
                            isValid = isValid,
                            error = if (!isValid) error(R.string.password_error) else null
                        )
                    )
                }
            }

            is PasswordRecoveryEvent.ValidateRecoveryCode -> {
                viewModelScope.launch {
                    val match = validateRecoveryCode(event.code)
                    state = state.copy(
                        recoveryCodeState = state.recoveryCodeState.copy(
                            match = match /* TODO(Implement Use case) */,
                            error = if (!match) error(R.string.verication_code_mismatch) else null
                        )
                    )
                }
            }

            is PasswordRecoveryEvent.ChangePasswordVisibility -> {
                state = state.copy(
                    newPasswordState = state.newPasswordState.copy(
                        isVisible = !state.newPasswordState.isVisible,
                    )
                )
            }
        }
    }

}