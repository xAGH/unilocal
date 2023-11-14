package eam.xagh.unilocal.business.domain.entities

import eam.xagh.unilocal.business.domain.constants.DayOfWeek

data class OpenDay(
    val day: DayOfWeek,
    val from: String,
    val until: String
)