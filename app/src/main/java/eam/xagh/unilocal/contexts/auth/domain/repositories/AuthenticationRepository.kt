package eam.xagh.unilocal.contexts.auth.domain.repositories

interface AuthenticationRepository {
    suspend fun validateNickname(nickname: String): Boolean
    suspend fun validateNicknameOrEmail(nicknameOrEmail: String): Boolean
    suspend fun validateEmail(email: String): Boolean
    suspend fun validatePassword(password: String): Boolean
    suspend fun validateName(name: String): Boolean
}