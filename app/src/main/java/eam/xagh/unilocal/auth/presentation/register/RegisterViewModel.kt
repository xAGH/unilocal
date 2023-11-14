package eam.xagh.unilocal.auth.presentation.register

import android.app.Application
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eam.xagh.unilocal.R
import eam.xagh.unilocal.auth.domain.usecases.user.RegisterUser
import eam.xagh.unilocal.auth.domain.usecases.validate.ValidateEmail
import eam.xagh.unilocal.auth.domain.usecases.validate.ValidateName
import eam.xagh.unilocal.auth.domain.usecases.validate.ValidateNickname
import eam.xagh.unilocal.auth.domain.usecases.validate.ValidatePassword
import eam.xagh.unilocal.auth.domain.repositories.UserRepository
import eam.xagh.unilocal.auth.domain.values.UserValue
import eam.xagh.unilocal.core.application.usecases.getCitiesUseCase
import eam.xagh.unilocal.core.domain.repositories.CityRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val validateEmail: ValidateEmail,
    private val validateNickname: ValidateNickname,
    private val validateName: ValidateName,
    private val validatePassword: ValidatePassword,
    private val cityRepository: CityRepository,
    private val registerUser: RegisterUser,
    private val userRepository: UserRepository,
    private val application: Application
) : ViewModel() {
    var state by mutableStateOf(RegisterState())
        private set

    init {
        viewModelScope.launch {
            val cities = getCitiesUseCase(cityRepository)
            state = state.copy(
                cityState = state.cityState.copy(
                    cities = cities,
                    isLoading = false
                )
            )
        }
    }

    private fun error(id: Int): String {
        return application.resources.getString(id)
    }

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.ValidateNickname -> {
                state = state.copy(
                    nicknameState = state.nicknameState.copy(
                        isValid = false, icon = null
                    )
                )
                viewModelScope.launch {
                    val isValid =
                        validateNickname(
                            event.nickname,
                        )
                    state = state.copy(
                        nicknameState = state.nicknameState.copy(
                            isValid = isValid,
                            icon = if (isValid) Icons.Filled.Check else Icons.Filled.Close,
                            error = if (!isValid) error(R.string.nickname_error) else null
                        ),
                    )
                }
            }

            is RegisterEvent.ChangePasswordVisibility -> {
                state = state.copy(
                    passwordState = state.passwordState.copy(
                        isVisible = !state.passwordState.isVisible,
                    )
                )
            }

            is RegisterEvent.ValidateEmail -> {
                viewModelScope.launch {
                    val isValid = validateEmail(event.email)
                    state = state.copy(
                        emailState = state.emailState.copy(
                            isValid = isValid,
                            error = if (!isValid) error(R.string.email_error) else null
                        ),
                    )
                }
            }

            is RegisterEvent.ValidatePassword -> {
                viewModelScope.launch {
                    val isValid =
                        validatePassword(event.password)
                    state = state.copy(
                        passwordState = state.passwordState.copy(
                            isValid = isValid,
                            error = if (!isValid) error(R.string.password_error) else null
                        )
                    )
                }
            }

            is RegisterEvent.ValidateName -> {
                viewModelScope.launch {
                    val isValid = validateName(event.name)
                    state = state.copy(
                        nameState = state.nameState.copy(
                            isValid = isValid,
                            error = if (!isValid) error(R.string.name_error) else null
                        )
                    )
                }
            }

            is RegisterEvent.RegisterUser -> {
                viewModelScope.launch {
                    state = state.copy(doingRegister = true)
                    val response = registerUser(
                        UserValue(
                            event.name,
                            event.email,
                            event.password,
                            state.cityState.cities.find { it == event.city }!!.id,
                            event.nickname
                        ), userRepository, application.applicationContext
                    )
                    state = state.copy(doingRegister = false)
                    event.onRegisterFinish(response)
                }
            }
        }
    }
}