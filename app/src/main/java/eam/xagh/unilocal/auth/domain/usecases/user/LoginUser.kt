package eam.xagh.unilocal.auth.domain.usecases.user

import eam.xagh.unilocal.auth.domain.repositories.UserRepository
import eam.xagh.unilocal.auth.domain.values.LoginResponse

interface LoginUser {
    suspend operator fun invoke(
        emailOrNickname: String,
        password: String,
        repository: UserRepository
    ): LoginResponse
}