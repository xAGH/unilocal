package eam.xagh.unilocal.contexts.auth.presentation.login

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eam.xagh.unilocal.R
import eam.xagh.unilocal.contexts.auth.infrastructure.repositories.AuthenticationRepositoryAdapter
import eam.xagh.unilocal.utils.isEmail
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepositoryAdapter,
    private val application: Application
): ViewModel() {
    var state by mutableStateOf(LoginState())
        private set

    private fun error(id: Int): String {
        return application.resources.getString(id)
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.ChangePasswordVisibility -> {
                state = state.copy(
                    passwordState = state.passwordState.copy(
                        isVisible = !state.passwordState.isVisible,
                    )
                )
            }

            is LoginEvent.ValidateNicknameOrEmail -> {
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

            is LoginEvent.ValidatePassword -> {
                viewModelScope.launch {
                    val isValid =
                        eam.xagh.unilocal.contexts.auth.application.usecases.validatePassword(
                            event.password,
                            authenticationRepository
                        )
                    state = state.copy(
                        passwordState = state.passwordState.copy(
                            isValid = isValid,
                            error = if (!isValid) error(R.string.password_error) else null
                        )
                    )
                }
            }

            is LoginEvent.Login -> {

            }
        }
    }
}