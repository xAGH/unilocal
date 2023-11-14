package eam.xagh.unilocal.auth.domain.usecases.validate

interface ValidateEmail {
    operator fun invoke (email: String): Boolean
}