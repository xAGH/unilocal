package eam.xagh.unilocal.business.domain.usecases.create

interface ValidateBusinessDescription {
    suspend operator fun invoke(businessDescription: String): Boolean
}