package eam.xagh.unilocal.business.application.usecases.create;

import eam.xagh.unilocal.business.domain.usecases.create.ValidateBusinessDescription

class ValidateBusinessDescriptionUseCase : ValidateBusinessDescription {
    override suspend fun invoke(businessDescription: String): Boolean {
        val words = businessDescription.split(" ").filter { it != "" }
        val hasMinWords = words.size > 2
        val wordsHasMinLength = words.all { it.isNotEmpty() }
        return hasMinWords && wordsHasMinLength
    }
}
