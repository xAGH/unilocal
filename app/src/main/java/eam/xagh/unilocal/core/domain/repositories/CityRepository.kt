package eam.xagh.unilocal.core.domain.repositories

import eam.xagh.unilocal.core.domain.entities.City

interface CityRepository {
    fun getCities(): List<City>
}