package eam.xagh.unilocal.business.application.usecases.create

import eam.xagh.unilocal.business.domain.entities.Business
import eam.xagh.unilocal.business.domain.repositories.BusinessRepository
import eam.xagh.unilocal.business.domain.usecases.create.CreateBusiness

class CreateBusinessUseCase : CreateBusiness {
    override suspend fun invoke(business: Business, repository: BusinessRepository): Boolean {
        return repository.createBusiness(business)
    }
}