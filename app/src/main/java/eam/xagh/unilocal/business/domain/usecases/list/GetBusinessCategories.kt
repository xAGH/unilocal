package eam.xagh.unilocal.business.domain.usecases.list

import eam.xagh.unilocal.business.domain.entities.BusinessCategory

interface GetBusinessCategories {
    operator fun invoke(): List<BusinessCategory>
}