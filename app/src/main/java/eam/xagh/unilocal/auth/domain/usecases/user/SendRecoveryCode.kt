package eam.xagh.unilocal.auth.domain.usecases.user

import eam.xagh.unilocal.auth.domain.repositories.UserRepository

interface SendRecoveryCode {
    suspend operator fun invoke(
        nicknameOrEmail: String,
        repository: UserRepository
    ): Boolean
}