package eam.xagh.unilocal.auth.presentation.password_recovery

import eam.xagh.unilocal.auth.presentation.states.NicknameOrEmailState
import eam.xagh.unilocal.auth.presentation.states.PasswordState


data class RecoveryCodeState(
    val isSent: Boolean = false,
    val match: Boolean =false,
    val error: String? = null
)

data class PasswordRecoveryState (
    val nicknameOrEmailState: NicknameOrEmailState = NicknameOrEmailState(),
    val recoveryCodeState: RecoveryCodeState = RecoveryCodeState(),
    val newPasswordState: PasswordState = PasswordState()
)