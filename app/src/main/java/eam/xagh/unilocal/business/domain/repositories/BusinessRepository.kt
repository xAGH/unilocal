package eam.xagh.unilocal.business.domain.repositories

import eam.xagh.unilocal.business.domain.entities.Business

interface BusinessRepository {
    suspend fun getBusiness(): List<Business>
    suspend fun createBusiness(business: Business): Boolean
}