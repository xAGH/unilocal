package eam.xagh.unilocal.business.infrastructure.mappers

import android.net.Uri
import com.google.firebase.firestore.DocumentSnapshot
import eam.xagh.unilocal.business.domain.constants.DayOfWeek
import eam.xagh.unilocal.business.domain.entities.Business
import eam.xagh.unilocal.business.domain.entities.OpenDay
import eam.xagh.unilocal.business.domain.values.BusinessValue
import java.util.UUID

fun businessToMap(business: Business): MutableMap<String, Any> {
    return mutableMapOf(
        "id" to business.id.toString(),
        "name" to business.name,
        "description" to business.description,
        "lat" to business.lat,
        "lon" to business.lon,
        "category" to business.category,
        "phones" to business.phones,
        "comments" to business.comments.map { it.toString() },
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

fun documentSnapshotToBusiness(document: DocumentSnapshot): Business {
    val schedules: List<OpenDay> = (document.get("schedule") as List<*>).map {
        it as Map<*, *>
        OpenDay(
            day = DayOfWeek.values().first { day -> day.value == it["day"] as String },
            until = it["until"] as String,
            from = it["from"] as String
        )
    }
    val images: List<Uri> = (document.get("images") as List<*>).map {
        it as String
        Uri.parse(it)
    }
    val phones: List<String> = (document.get("phones") as List<*>).map {
        it as String
        it
    }
    val comments: List<UUID> = (document.get("comments") as List<*>).map {
        it as String
        UUID.fromString(it)
    }
    return BusinessValue(
        id = UUID.fromString(document.id),
        schedule = schedules,
        ratings = document.get("ratings").toString().toFloat(),
        phones = phones,
        owner = UUID.fromString(document.get("owner").toString()),
        lat = document.get("lat").toString().toDouble(),
        lon = document.get("lon").toString().toDouble(),
        images = images,
        description = document.get("description").toString(),
        name = document.get("name").toString(),
        comments = comments,
        category = document.get("category").toString(),
        approved = document.get("approved").toString().toBoolean()
    )
}