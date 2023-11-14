package eam.xagh.unilocal.business.domain.values

import android.net.Uri
import eam.xagh.unilocal.business.domain.entities.Business
import eam.xagh.unilocal.business.domain.entities.BusinessCategory
import eam.xagh.unilocal.business.domain.entities.Comment
import eam.xagh.unilocal.business.domain.entities.OpenDay
import java.util.UUID

class BusinessValue(
    override val name: String,
    override val description: String,
    override val lat: Double,
    override val lon: Double,
    override val category: String,
    override val phones: List<String>,
    override val comments: List<UUID> = listOf(),
    override val schedule: List<OpenDay>,
    override val ratings: Float = 0f,
    override val owner: UUID = UUID.randomUUID(),
    override val approved: Boolean = false,
    override val images: List<Uri>,
    override val id: UUID = UUID.randomUUID()
) : Business