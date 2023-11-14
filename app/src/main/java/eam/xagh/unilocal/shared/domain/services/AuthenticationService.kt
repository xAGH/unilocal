package eam.xagh.unilocal.shared.domain.services

import eam.xagh.unilocal.auth.domain.entities.User

interface AuthenticationService {
    val user: User?
    val isAuthenticated: Boolean
    fun closeSession()
    fun setUser(user: User)
}