package eam.xagh.unilocal.business.application.usecases.list

import eam.xagh.unilocal.business.domain.entities.Business
import eam.xagh.unilocal.business.domain.repositories.BusinessRepository
import eam.xagh.unilocal.business.domain.usecases.list.GetBusiness

class GetBusinessUseCase : GetBusiness {
    override suspend operator fun invoke(repository: BusinessRepository): List<Business> {
        return repository.getBusiness()
    }
}