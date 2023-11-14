package eam.xagh.unilocal.auth.application.usecases.validate

import eam.xagh.unilocal.auth.domain.usecases.validate.ValidateName


class ValidateNameUseCase : ValidateName {
    override operator fun invoke(name: String): Boolean {
        val words = name.split(" ").filter { it != "" }
        val hasMinWords = words.size in 2..6
        val wordsHasMinLength = words.all { it.length > 1 }
        return hasMinWords && wordsHasMinLength
    }
}