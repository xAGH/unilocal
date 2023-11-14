package eam.xagh.unilocal.core.domain.values

import eam.xagh.unilocal.core.domain.entities.City
import java.util.UUID

class CityValue(
    override val name: String,
    override val lat: Double,
    override val lon: Double
) : City {
    override val id: UUID = UUID.randomUUID()
}