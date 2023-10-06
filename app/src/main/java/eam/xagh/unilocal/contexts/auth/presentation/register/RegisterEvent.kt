package eam.xagh.unilocal.contexts.auth.presentation.register

sealed interface RegisterEvent {
    data class ValidateEmail(val email: String) : RegisterEvent
    data class ValidatePassword(val password: String) : RegisterEvent
    data class ValidateNickname(val nickname: String) : RegisterEvent
    data class ValidateName(val name: String) : RegisterEvent
    data class RegisterUser(
        val name: String,
        val email: String,
        val password: String,
        val city: String,
        val nickname: String
    ): RegisterEvent
    object ChangePasswordVisibility : RegisterEvent
    object GetCities : RegisterEvent

}