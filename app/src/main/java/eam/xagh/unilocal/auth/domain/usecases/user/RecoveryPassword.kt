package eam.xagh.unilocal.auth.domain.usecases.user

import eam.xagh.unilocal.auth.domain.repositories.UserRepository

interface RecoveryPassword {
    suspend operator fun invoke(
        nicknameOrEmail: String,
        newPassword: String,
        repository: UserRepository
    ): Boolean
}