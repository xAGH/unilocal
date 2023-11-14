package eam.xagh.unilocal.auth.domain.values

import eam.xagh.unilocal.auth.domain.entities.User

data class LoginResponse(
    val success: Boolean,
    val user: User?
)