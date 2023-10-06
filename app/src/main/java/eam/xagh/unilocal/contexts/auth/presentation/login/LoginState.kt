package eam.xagh.unilocal.contexts.auth.presentation.login

import eam.xagh.unilocal.contexts.auth.presentation.states.NicknameOrEmailState
import eam.xagh.unilocal.contexts.auth.presentation.states.PasswordState

data class LoginState(
    val nicknameOrEmailState: NicknameOrEmailState = NicknameOrEmailState(),
    val passwordState: PasswordState = PasswordState(),
)