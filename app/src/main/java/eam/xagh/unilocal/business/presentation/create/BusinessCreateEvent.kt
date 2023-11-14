package eam.xagh.unilocal.business.presentation.create

import android.net.Uri
import com.google.android.gms.maps.model.LatLng
import eam.xagh.unilocal.business.domain.entities.OpenDay

sealed interface BusinessCreateEvent {
    data class ValidateBusinessName(val businessName: String) : BusinessCreateEvent
    data class ValidateBusinessDescription(val businessDescription: String) : BusinessCreateEvent
    data class SaveCategory(val businessCategory: String) : BusinessCreateEvent
    data class SaveContact(
        val businessPhones: List<String>, val businessImages: List<Uri>
    ) : BusinessCreateEvent

    data class SaveLocation(val location: LatLng) : BusinessCreateEvent
    data class SaveSchedules(val schedules: List<OpenDay>) : BusinessCreateEvent
    data class CreateBusiness(val onFinish: (Boolean) -> Unit) : BusinessCreateEvent
}