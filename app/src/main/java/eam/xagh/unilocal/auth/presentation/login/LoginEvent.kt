package eam.xagh.unilocal.auth.presentation.login

sealed interface LoginEvent {
    data class ValidateNicknameOrEmail(val nicknameOrEmail: String) : LoginEvent
    data class ValidatePassword(val password: String) : LoginEvent
    data class Login(
        val nicknameOrEmail: String,
        val password: String,
        val byBiometricMethod: Boolean,
        val onLoginFinish: (loginSuccess: Boolean) -> Unit
    ) : LoginEvent

    object ChangePasswordVisibility : LoginEvent
}