package eam.xagh.unilocal.contexts.auth.application.usecases

import eam.xagh.unilocal.contexts.auth.domain.repositories.AuthenticationRepository


suspend fun validateEmail(email: String, repository: AuthenticationRepository): Boolean {
    return repository.validateEmail(email)
}

suspend fun validateNickname(nickname: String, repository: AuthenticationRepository): Boolean {
    return repository.validateNickname(nickname)
}

suspend fun validatePassword(password: String, repository: AuthenticationRepository): Boolean {
    return repository.validatePassword(password)
}

suspend fun validateName(name: String, repository: AuthenticationRepository): Boolean {
    return repository.validateName(name)
}

suspend fun validateNicknameOrEmail(
    nicknameOrEmail: String,
    repository: AuthenticationRepository
): Boolean {
    return repository.validateNicknameOrEmail(nicknameOrEmail)
}