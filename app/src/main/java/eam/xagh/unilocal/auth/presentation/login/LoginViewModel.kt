package eam.xagh.unilocal.auth.presentation.login

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eam.xagh.unilocal.R
import eam.xagh.unilocal.auth.domain.repositories.UserRepository
import eam.xagh.unilocal.auth.domain.usecases.user.GetUser
import eam.xagh.unilocal.auth.domain.usecases.user.LoginUser
import eam.xagh.unilocal.auth.domain.usecases.validate.ValidateEmail
import eam.xagh.unilocal.auth.domain.usecases.validate.ValidateNickname
import eam.xagh.unilocal.auth.domain.usecases.validate.ValidatePassword
import eam.xagh.unilocal.shared.presentation.authentication.AuthenticationViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateNickname: ValidateNickname,
    private val validatePassword: ValidatePassword,
    private val validateEmail: ValidateEmail,
    private val userRepository: UserRepository,
    private val loginUser: LoginUser,
    private val authenticationViewModel: AuthenticationViewModel,
    private val application: Application
) : ViewModel() {
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
                val value = event.nicknameOrEmail
                val isValid =
                    if (value.contains("@")) validateEmail(value) else validateNickname(value)
                state = state.copy(
                    nicknameOrEmailState = state.nicknameOrEmailState.copy(
                        isValid = isValid, error = if (!isValid) {
                            if (event.nicknameOrEmail.contains("@")) error(R.string.email_error)
                            else error(R.string.nickname_error)
                        } else null
                    ),
                )
            }

            is LoginEvent.ValidatePassword -> {
                val isValid = validatePassword(event.password)
                state = state.copy(
                    passwordState = state.passwordState.copy(
                        isValid = isValid,
                        error = if (!isValid) error(R.string.password_error) else null
                    )
                )
            }

            is LoginEvent.Login -> {
                viewModelScope.launch {
                    state = state.copy(doingLogin = true)
                    var nicknameOrEmail = event.nicknameOrEmail
                    var password = event.password
                    if (event.byBiometricMethod) {
                        val lastUser = authenticationViewModel.state.user!!
                        nicknameOrEmail = lastUser.email
                        password = lastUser.password
                    }
                    val loginResponse =
                        loginUser(nicknameOrEmail, password, userRepository)
                    state = state.copy(doingLogin = false)
                    if (loginResponse.success) authenticationViewModel.login(loginResponse.user!!)
                    event.onLoginFinish(loginResponse.success)
                }
            }
        }
    }
}