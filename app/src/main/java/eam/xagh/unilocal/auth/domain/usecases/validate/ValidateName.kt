package eam.xagh.unilocal.auth.domain.usecases.validate

interface ValidateName {
    operator fun invoke(name: String): Boolean
}