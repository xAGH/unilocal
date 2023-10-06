package eam.xagh.unilocal.contexts.auth.presentation.login

sealed interface LoginEvent {
    data class ValidateNicknameOrEmail(val nicknameOrEmail: String) : LoginEvent
    data class ValidatePassword(val password: String) : LoginEvent
    data class Login(val nicknameOrEmail: String , val password: String) : LoginEvent
    object ChangePasswordVisibility : LoginEvent

}