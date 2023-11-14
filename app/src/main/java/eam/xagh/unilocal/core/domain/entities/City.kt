package eam.xagh.unilocal.core.domain.entities

import java.util.UUID

interface City {
    val id: UUID
    val name: String
    val lat: Double
    val lon: Double
}