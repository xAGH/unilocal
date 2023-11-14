package eam.xagh.unilocal.business.domain.usecases.create

import eam.xagh.unilocal.business.domain.entities.Business
import eam.xagh.unilocal.business.domain.repositories.BusinessRepository

interface CreateBusiness {
    suspend operator fun invoke(business: Business, repository: BusinessRepository): Boolean
}