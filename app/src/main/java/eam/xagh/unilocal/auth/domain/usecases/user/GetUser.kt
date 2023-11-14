package eam.xagh.unilocal.auth.domain.usecases.user

import eam.xagh.unilocal.auth.domain.entities.User
import eam.xagh.unilocal.auth.domain.repositories.UserRepository

interface GetUser {
    suspend operator fun invoke(
        field: String,
        value: Any,
        repository: UserRepository
    ): User?
}