package eam.xagh.unilocal.auth.presentation.register

import eam.xagh.unilocal.auth.domain.values.RegisterResponse
import eam.xagh.unilocal.core.domain.entities.City

sealed interface RegisterEvent {
    data class ValidateEmail(val email: String) : RegisterEvent
    data class ValidatePassword(val password: String) : RegisterEvent
    data class ValidateNickname(val nickname: String) : RegisterEvent
    data class ValidateName(val name: String) : RegisterEvent
    data class RegisterUser(
        val name: String,
        val email: String,
        val password: String,
        val city: City,
        val nickname: String,
        val onRegisterFinish: (RegisterResponse) -> Unit
    ) : RegisterEvent

    object ChangePasswordVisibility : RegisterEvent
}