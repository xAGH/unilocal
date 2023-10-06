package eam.xagh.unilocal.contexts.auth.infrastructure.repositories

import eam.xagh.unilocal.contexts.auth.domain.repositories.AuthenticationRepository
import eam.xagh.unilocal.utils.isEmail

class AuthenticationRepositoryAdapter : AuthenticationRepository {
    override suspend fun validateNickname(nickname: String): Boolean {
        return regexMatch("""^[a-zA-Z][a-zA-Z0-9]{3,}${'$'}""", nickname)
    }

    override suspend fun validateNicknameOrEmail(nicknameOrEmail: String): Boolean {
        return if(isEmail(nicknameOrEmail)) validateEmail(nicknameOrEmail)
            else validateNickname(nicknameOrEmail)
    }

    override suspend fun validateEmail(email: String): Boolean {
        return regexMatch("""^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$""", email)
    }

    override suspend fun validatePassword(password: String): Boolean {
        return password.length > 7
    }

    override suspend fun validateName(name: String): Boolean {
        val words = name.split(" ").filter { it != "" }
        val hasMinWords = words.size in 2..6
        val wordsHasMinLength = words.all { it.length > 1 }
        return hasMinWords && wordsHasMinLength
    }

    private fun regexMatch(
        pattern: String,
        value: String
    ): Boolean {
        return Regex(pattern).matches(value)
    }
}