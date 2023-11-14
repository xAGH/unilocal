package eam.xagh.unilocal.auth.presentation.password_recovery

sealed interface PasswordRecoveryEvent {
    data class SendRecoveryCode(val nicknameOrEmail: String): PasswordRecoveryEvent
    data class ValidateRecoveryCode(val code: String, val nicknameOrEmail: String):
        PasswordRecoveryEvent
    data class ValidateNicknameOrEmail(val nicknameOrEmail: String) : PasswordRecoveryEvent
    data class ValidatePassword(val password: String) : PasswordRecoveryEvent
    data class RecoveryPassword(val nicknameOrEmail: String , val newPassword: String) :
        PasswordRecoveryEvent
    object ChangePasswordVisibility : PasswordRecoveryEvent
}