package eam.xagh.unilocal.core.infrastructure.repositories

import eam.xagh.unilocal.core.domain.entities.City
import eam.xagh.unilocal.core.domain.repositories.CityRepository
import eam.xagh.unilocal.core.domain.values.CityValue

class CityRepositoryAdapter : CityRepository {
    override fun getCities(): List<City> {
        return listOf(
            CityValue("Armenia", 12.0, 12.0),
            CityValue("Calarca", 12.0, 12.0),
            CityValue("Manizales", 12.0, 12.0),
            CityValue("Pereira", 12.0, 12.0),
            CityValue("Bogota", 12.0, 12.0),
        )
    }
}