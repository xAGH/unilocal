package eam.xagh.unilocal.business.infrastructure.mappers

import eam.xagh.unilocal.business.domain.entities.Business

fun businessToMap(business: Business): MutableMap<String, Any> {
    return mutableMapOf(
        "id" to business.id.toString(),
        "name" to business.name,
        "description" to business.description,
        "lat" to business.lat,
        "lon" to business.lon,
        "category" to business.category,
        "phones" to business.phones,
        "comments" to business.comments,
        "schedule" to business.schedule.map { schedule ->
            mapOf(
                "day" to schedule.day.value,
                "from" to schedule.from,
                "until" to schedule.until
            )
        },
        "ratings" to business.ratings,
        "approved" to business.approved,
        "owner" to business.owner.toString(),
    )
}