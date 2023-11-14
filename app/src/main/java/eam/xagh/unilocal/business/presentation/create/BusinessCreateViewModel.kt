package eam.xagh.unilocal.business.presentation.create

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eam.xagh.unilocal.R
import eam.xagh.unilocal.business.domain.repositories.BusinessRepository
import eam.xagh.unilocal.business.domain.usecases.create.CreateBusiness
import eam.xagh.unilocal.business.domain.usecases.create.ValidateBusinessDescription
import eam.xagh.unilocal.business.domain.usecases.create.ValidateBusinessName
import eam.xagh.unilocal.business.domain.usecases.list.GetBusinessCategories
import eam.xagh.unilocal.business.domain.values.BusinessValue
import eam.xagh.unilocal.shared.presentation.authentication.AuthenticationViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusinessCreateViewModel @Inject constructor(
    private val application: Application,
    private val validateBusinessName: ValidateBusinessName,
    private val validateBusinessDescription: ValidateBusinessDescription,
    private val createBusiness: CreateBusiness,
    private val authenticationViewModel: AuthenticationViewModel,
    private val businessRepository: BusinessRepository,
    getBusinessCategories: GetBusinessCategories
) : ViewModel() {

    var state by mutableStateOf(BusinessCreateState())
        private set

    val businessCategories = getBusinessCategories()

    private fun error(id: Int): String {
        return application.resources.getString(id)
    }

    fun onEvent(event: BusinessCreateEvent) {
        when (event) {
            is BusinessCreateEvent.ValidateBusinessName -> {
                viewModelScope.launch {
                    val isValid = validateBusinessName(event.businessName)
                    state = state.copy(
                        businessNameState = state.businessNameState.copy(
                            isValid = isValid,
                            value = event.businessName,
                            error = if (!isValid) error(R.string.business_name_error) else null
                        )
                    )
                }
            }

            is BusinessCreateEvent.ValidateBusinessDescription -> {
                viewModelScope.launch {
                    val isValid = validateBusinessDescription(event.businessDescription)
                    state = state.copy(
                        businessDescriptionState = state.businessDescriptionState.copy(
                            isValid = isValid,
                            value = event.businessDescription,
                            error = if (!isValid) error(R.string.business_name_error) else null
                        )
                    )
                }
            }

            is BusinessCreateEvent.SaveCategory -> {
                state = state.copy(
                    businessCategory = event.businessCategory

                )
            }

            is BusinessCreateEvent.SaveContact -> {
                state = state.copy(
                    businessPhones = event.businessPhones,
                    businessImages = event.businessImages
                )
            }

            is BusinessCreateEvent.SaveLocation -> state =
                state.copy(businessLocation = event.location)


            is BusinessCreateEvent.SaveSchedules -> state =
                state.copy(businessSchedule = event.schedules)

            is BusinessCreateEvent.CreateBusiness -> {
                val business = BusinessValue(
                    name = state.businessNameState.value,
                    approved = false,
                    category = state.businessCategory,
                    comments = listOf(),
                    description = state.businessDescriptionState.value,
                    images = state.businessImages,
                    lat = 0.0,
                    lon = 0.0,
                    owner = authenticationViewModel.state.user!!.id,
                    phones = state.businessPhones,
                    ratings = 0f,
                    schedule = state.businessSchedule
                )
                viewModelScope.launch {
                    state = state.copy(creatingBusiness = true)
                    val success = createBusiness(business, businessRepository)
                    state = state.copy(creatingBusiness = false)
                    event.onFinish(success)
                }
            }
        }
    }

}