package eam.xagh.unilocal.business.application.usecases.create

import eam.xagh.unilocal.business.domain.usecases.create.ValidateBusinessName

class ValidateBusinessNameUseCase : ValidateBusinessName {
    override suspend fun invoke(businessName: String): Boolean {
        val words = businessName.split(" ").filter { it != "" }
        val hasMinWords = words.size in 1..6
        val wordsHasMinLength = words.all { it.length >= 2 }
        return hasMinWords && wordsHasMinLength
    }
}