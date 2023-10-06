package eam.xagh.unilocal.contexts.auth.presentation.register

import android.app.Application
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eam.xagh.unilocal.R
import eam.xagh.unilocal.contexts.auth.infrastructure.repositories.AuthenticationRepositoryAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepositoryAdapter,
    private val application: Application
) : ViewModel() {
    var state by mutableStateOf(RegisterState())
        private set

    private fun error(id: Int): String {
        return application.resources.getString(id)
    }

    fun onEvent(event: RegisterEvent): Any {
        return when (event) {
            is RegisterEvent.ValidateNickname -> {
                state = state.copy(
                    nicknameState = state.nicknameState.copy(
                        isValid = false, icon = null
                    )
                )
                viewModelScope.launch {
                    delay(2000)
                    val isValid =
                        eam.xagh.unilocal.contexts.auth.application.usecases.validateNickname(
                            event.nickname,
                            authenticationRepository
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
                    val isValid = eam.xagh.unilocal.contexts.auth.application.usecases.validateEmail(
                        event.email,
                        authenticationRepository
                    )
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

            is RegisterEvent.ValidateName -> {
                viewModelScope.launch {
                    val isValid = eam.xagh.unilocal.contexts.auth.application.usecases.validateName(
                        event.name,
                        authenticationRepository
                    )
                    state = state.copy(
                        nameState = state.nameState.copy(
                            isValid = isValid,
                            error = if (!isValid) error(R.string.name_error) else null
                        )
                    )
                }
            }

            is RegisterEvent.GetCities -> {
                state = state.copy(
                    cityState = state.cityState.copy(
                        cities = getCities(), /* TODO(Get Cities) */
                        error = error(R.string.city_error)
                    )
                )
            }

            is RegisterEvent.RegisterUser -> {
                /* TODO(Register User) */
            }
        }
    }

    private fun getCities(): List<String> {
        return listOf("Opcion1", "Opcion2", "Opcion3")
    }
}