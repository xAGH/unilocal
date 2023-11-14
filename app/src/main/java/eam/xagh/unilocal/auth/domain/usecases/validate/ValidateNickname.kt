package eam.xagh.unilocal.auth.domain.usecases.validate

interface ValidateNickname {
    operator fun invoke(nickname: String): Boolean
}