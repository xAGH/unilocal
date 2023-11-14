package eam.xagh.unilocal.auth.application.usecases.user

import eam.xagh.unilocal.auth.domain.entities.User
import eam.xagh.unilocal.auth.domain.repositories.UserRepository
import eam.xagh.unilocal.auth.domain.usecases.user.GetUser

class GetUserUseCase : GetUser {
    override suspend fun invoke(field: String, value: Any, repository: UserRepository): User? {
        return repository.getUser(field, value)
    }

}