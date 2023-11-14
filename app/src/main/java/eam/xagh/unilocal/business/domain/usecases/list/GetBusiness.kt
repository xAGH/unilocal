package eam.xagh.unilocal.business.domain.usecases.list

import eam.xagh.unilocal.business.domain.entities.Business
import eam.xagh.unilocal.business.domain.repositories.BusinessRepository
import java.util.UUID

interface GetBusiness {
    suspend operator fun invoke(owner: UUID, repository: BusinessRepository): List<Business>
}