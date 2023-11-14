package eam.xagh.unilocal.business.domain.repositories

import eam.xagh.unilocal.business.domain.entities.Business
import java.util.UUID

interface BusinessRepository {
    suspend fun getBusiness(owner: UUID): List<Business>
    suspend fun createBusiness(business: Business): Boolean
}