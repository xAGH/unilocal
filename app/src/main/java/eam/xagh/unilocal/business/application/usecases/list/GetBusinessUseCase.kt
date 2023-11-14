package eam.xagh.unilocal.business.application.usecases.list

import eam.xagh.unilocal.business.domain.entities.Business
import eam.xagh.unilocal.business.domain.repositories.BusinessRepository
import eam.xagh.unilocal.business.domain.usecases.list.GetBusiness
import java.util.UUID

class GetBusinessUseCase : GetBusiness {
    override suspend fun invoke(owner: UUID, repository: BusinessRepository): List<Business> {
        return repository.getBusiness(owner)
    }
}