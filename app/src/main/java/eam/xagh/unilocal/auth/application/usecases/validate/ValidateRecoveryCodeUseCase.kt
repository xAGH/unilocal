package eam.xagh.unilocal.auth.application.usecases.validate

import eam.xagh.unilocal.auth.domain.usecases.validate.ValidateRecoveryCode

class ValidateRecoveryCodeUseCase : ValidateRecoveryCode {
    override operator fun invoke(code: String): Boolean {
        val pattern = "^[0-9]{6}\$"
        return Regex(pattern).matches(code)
    }
}