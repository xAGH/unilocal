package eam.xagh.unilocal.auth.application.usecases.user

import eam.xagh.unilocal.auth.domain.usecases.user.LoginUser
import eam.xagh.unilocal.auth.domain.entities.User
import eam.xagh.unilocal.auth.domain.repositories.UserRepository
import eam.xagh.unilocal.auth.domain.values.LoginResponse

class LoginUserUseCase : LoginUser {
    override suspend operator fun invoke(
        emailOrNickname: String,
        password: String,
        repository: UserRepository
    ): LoginResponse {
        return repository.signIn(emailOrNickname, password)
    }
}