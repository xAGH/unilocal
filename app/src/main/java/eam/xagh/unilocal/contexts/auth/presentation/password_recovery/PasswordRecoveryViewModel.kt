package eam.xagh.unilocal.contexts.auth.presentation.password_recovery

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eam.xagh.unilocal.R
import eam.xagh.unilocal.contexts.auth.domain.repositories.AuthenticationRepository
import eam.xagh.unilocal.utils.isEmail
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordRecoveryViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val application: Application
) : ViewModel() {
    var state by mutableStateOf(PasswordRecoveryState())
        private set

    private fun error(id: Int): String {
        return application.resources.getString(id)
    }

    fun onEvent(event: PasswordRecoveryEvent) {
        when(event) {
            is PasswordRecoveryEvent.RecoveryPassword -> {
                Toast.makeText(application.applicationContext, "Ok", Toast.LENGTH_SHORT).show()
            }
            is PasswordRecoveryEvent.SendRecoveryCode -> {
                if (event.nicknameOrEmail in listOf("test11", "test22", "test33", "alejito23001@gmail.com")){
                    state = state.copy(
                        recoveryCodeState = state.recoveryCodeState.copy(
                            isSent = true
                        )
                    )
                }
                else {
                    state = state.copy(
                        recoveryCodeState = state.recoveryCodeState.copy(
                            isSent = false,
                        ),
                        nicknameOrEmailState = state.nicknameOrEmailState.copy(
                            error = error(if(isEmail(event.nicknameOrEmail)) R.string.email_not_found else R.string.nickname_not_found)
                        )
                    )
                }
            }
            is PasswordRecoveryEvent.ValidateNicknameOrEmail -> {
                viewModelScope.launch {
                    val isValid =
                        eam.xagh.unilocal.contexts.auth.application.usecases.validateNicknameOrEmail(
                            event.nicknameOrEmail,
                            authenticationRepository
                        )
                    state = state.copy(
                        nicknameOrEmailState = state.nicknameOrEmailState.copy(
                            isValid = isValid,
                            error = if (!isValid) {
                                if(isEmail(event.nicknameOrEmail)) error(R.string.email_error)
                                else error(R.string.nickname_error)
                            } else null
                        ),
                    )
                }
            }
            is PasswordRecoveryEvent.ValidatePassword -> {
                viewModelScope.launch {
                    val isValid =
                        eam.xagh.unilocal.contexts.auth.application.usecases.validatePassword(
                            event.password,
                            authenticationRepository
                        )
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
                    val match = event.code == "000000"
                    Log.i("Code value", event.code)
                    state = state.copy(
                        recoveryCodeState = state.recoveryCodeState.copy(
                            match = match /* TODO(Implement Usecase) */,
                            error = if(!match) error(R.string.verication_code_mismatch) else null
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