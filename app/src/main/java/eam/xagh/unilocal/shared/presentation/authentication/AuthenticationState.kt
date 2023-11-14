package eam.xagh.unilocal.shared.presentation.authentication

import eam.xagh.unilocal.auth.domain.entities.User

data class AuthenticationState(
    val isAuthenticated: Boolean,
    val isModerator: Boolean,
    val user: User?
)