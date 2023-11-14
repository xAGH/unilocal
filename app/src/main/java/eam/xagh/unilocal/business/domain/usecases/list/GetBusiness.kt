package eam.xagh.unilocal.business.domain.usecases.list

import eam.xagh.unilocal.business.domain.entities.Business
import eam.xagh.unilocal.business.domain.repositories.BusinessRepository

interface GetBusiness {
    suspend operator fun invoke(repository: BusinessRepository): List<Business>
}