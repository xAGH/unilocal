package eam.xagh.unilocal.auth.application.usecases.validate

import eam.xagh.unilocal.auth.domain.usecases.validate.ValidateEmail

class ValidateEmailUseCase : ValidateEmail {
    override operator fun invoke(email: String): Boolean {
        val pattern = """^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$"""
        return Regex(pattern).matches(email)
    }
}