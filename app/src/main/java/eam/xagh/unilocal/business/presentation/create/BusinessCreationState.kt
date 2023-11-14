package eam.xagh.unilocal.business.presentation.create

import android.net.Uri
import com.google.android.gms.maps.model.LatLng
import eam.xagh.unilocal.business.domain.constants.DayOfWeek
import eam.xagh.unilocal.business.domain.entities.OpenDay

data class BusinessNameState(
    val isValid: Boolean = false, val value: String = "", val error: String? = null
)

data class BusinessDescriptionState(
    val isValid: Boolean = false, val value: String = "", val error: String? = null
)


data class BusinessCreateState(
    val businessNameState: BusinessNameState = BusinessNameState(),
    val businessDescriptionState: BusinessDescriptionState = BusinessDescriptionState(),
    val businessCategory: String = "",
    val businessPhones: List<String> = listOf(""),
    val businessImages: List<Uri> = listOf(),
    val businessLocation: LatLng? = null,
    val businessSchedule: List<OpenDay> = listOf(),
    val creatingBusiness: Boolean = false
)