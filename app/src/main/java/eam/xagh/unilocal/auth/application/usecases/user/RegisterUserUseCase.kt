package eam.xagh.unilocal.auth.application.usecases.user

import android.content.Context
import eam.xagh.unilocal.auth.domain.usecases.user.RegisterUser
import eam.xagh.unilocal.auth.domain.entities.User
import eam.xagh.unilocal.auth.domain.repositories.UserRepository
import eam.xagh.unilocal.auth.domain.values.RegisterResponse

class RegisterUserUseCase : RegisterUser {
    override suspend operator fun invoke(user: User, repository: UserRepository, context: Context): RegisterResponse {
        return repository.registerUser(user, context)
    }
}