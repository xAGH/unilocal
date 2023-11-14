package eam.xagh.unilocal.auth.application.usecases.user

import eam.xagh.unilocal.auth.domain.usecases.user.SendRecoveryCode
import eam.xagh.unilocal.auth.domain.repositories.UserRepository

class SendRecoveryCodeUseCase : SendRecoveryCode {
    override suspend fun invoke(
        nicknameOrEmail: String,
        repository: UserRepository
    ): Boolean {
        return repository.sendRecoveryCode(nicknameOrEmail)
    }
}