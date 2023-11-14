package eam.xagh.unilocal.business.domain.entities

import android.net.Uri
import java.util.UUID

interface Business {
    val id: UUID
    val name: String
    val description: String
    val lat: Double
    val lon: Double
    val category: String
    val phones: List<String>
    val comments: List<UUID>
    val schedule: List<OpenDay>
    val ratings: Float
    val approved: Boolean
    val images: List<Uri>
    val owner: UUID
}