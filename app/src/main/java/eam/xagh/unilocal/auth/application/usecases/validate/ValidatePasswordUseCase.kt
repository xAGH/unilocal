package eam.xagh.unilocal.auth.application.usecases.validate

import eam.xagh.unilocal.auth.domain.usecases.validate.ValidatePassword

class ValidatePasswordUseCase : ValidatePassword {
    override operator fun invoke(password: String): Boolean {
        return password.length > 7
    }
}