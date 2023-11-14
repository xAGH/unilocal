package eam.xagh.unilocal.auth.application.usecases.user

import eam.xagh.unilocal.auth.domain.usecases.user.RecoveryPassword
import eam.xagh.unilocal.auth.domain.repositories.UserRepository

class RecoveryPasswordUseCase : RecoveryPassword {
    override suspend operator fun invoke(
        nicknameOrEmail: String,
        newPassword: String,
        repository: UserRepository
    ): Boolean {
        return repository.recoveryPassword(nicknameOrEmail, newPassword)
    }
}