package eam.xagh.unilocal.auth.application.usecases.validate

import eam.xagh.unilocal.auth.domain.usecases.validate.ValidateNickname

class ValidateNicknameUseCase : ValidateNickname {
    override operator fun invoke(nickname: String): Boolean {
        val pattern = """^[a-zA-Z][a-zA-Z0-9]{3,}${'$'}"""
        return Regex(pattern).matches(nickname)
    }
}