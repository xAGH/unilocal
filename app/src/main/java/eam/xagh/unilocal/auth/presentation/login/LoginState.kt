package eam.xagh.unilocal.auth.presentation.login

import eam.xagh.unilocal.auth.presentation.states.NicknameOrEmailState
import eam.xagh.unilocal.auth.presentation.states.PasswordState

data class LoginState(
    val nicknameOrEmailState: NicknameOrEmailState = NicknameOrEmailState(),
    val passwordState: PasswordState = PasswordState(),
    val doingLogin: Boolean = false
)