package eam.xagh.unilocal.auth.domain.repositories

import android.content.Context
import eam.xagh.unilocal.auth.domain.entities.User
import eam.xagh.unilocal.auth.domain.values.LoginResponse
import eam.xagh.unilocal.auth.domain.values.RegisterResponse

interface UserRepository {
    suspend fun registerUser(user: User, context: Context): RegisterResponse
    suspend fun getUser(field: String, value: Any): User?
    suspend fun sendRecoveryCode(nicknameOrEmail: String): Boolean
    suspend fun recoveryPassword(nicknameOrEmail: String, newPassword: String): Boolean
    suspend fun signIn(emailOrNickname: String, password: String): LoginResponse
}

