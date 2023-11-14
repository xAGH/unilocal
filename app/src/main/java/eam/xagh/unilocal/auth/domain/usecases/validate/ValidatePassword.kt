package eam.xagh.unilocal.auth.domain.usecases.validate

interface ValidatePassword {
    operator fun invoke(password: String): Boolean
}