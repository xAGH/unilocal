package eam.xagh.unilocal.business.domain.usecases.create

interface ValidateBusinessName {
    suspend operator fun invoke(businessName: String): Boolean
}