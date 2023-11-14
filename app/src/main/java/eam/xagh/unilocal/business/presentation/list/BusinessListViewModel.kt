package eam.xagh.unilocal.business.presentation.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import eam.xagh.unilocal.business.domain.entities.Business
import eam.xagh.unilocal.business.domain.repositories.BusinessRepository
import eam.xagh.unilocal.business.domain.usecases.list.GetBusiness
import eam.xagh.unilocal.shared.presentation.authentication.AuthenticationViewModel
import javax.inject.Inject

@HiltViewModel
class BusinessListViewModel @Inject constructor(
    private val authenticationViewModel: AuthenticationViewModel,
    private val getBusiness: GetBusiness,
    private val businessRepository: BusinessRepository

) : ViewModel() {
    var state by mutableStateOf(BusinessListState())
        private set

    suspend fun getBusiness(): Map<String, List<Business>> {
        val businessByCategory = mutableMapOf<String, MutableList<Business>>()
        val business = getBusiness(businessRepository)
        business.forEach {
            val category = it.category
            if (businessByCategory.containsKey(category)) {
                businessByCategory[category]!!.add(it)
            } else {
                businessByCategory[category] = mutableListOf(it)
            }
        }
        return businessByCategory
    }
}