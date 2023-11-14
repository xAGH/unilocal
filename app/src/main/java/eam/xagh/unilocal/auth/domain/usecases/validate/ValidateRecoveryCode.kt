package eam.xagh.unilocal.auth.domain.usecases.validate

interface ValidateRecoveryCode {
    operator fun invoke(code: String): Boolean
}