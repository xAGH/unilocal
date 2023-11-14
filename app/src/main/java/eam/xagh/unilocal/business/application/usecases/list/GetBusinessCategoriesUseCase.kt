package eam.xagh.unilocal.business.application.usecases.list

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.Museum
import androidx.compose.material.icons.filled.Restaurant
import eam.xagh.unilocal.business.domain.entities.BusinessCategory
import eam.xagh.unilocal.business.domain.usecases.list.GetBusinessCategories

val businessCategories = listOf(
    BusinessCategory(
        "Restaurant",
        Icons.Filled.Restaurant
    ),
    BusinessCategory(
        "Coffee",
        Icons.Filled.Coffee
    ),
    BusinessCategory(
        "Fast Food",
        Icons.Filled.Fastfood
    ),
    BusinessCategory(
        "Museum",
        Icons.Filled.Museum
    ),
    BusinessCategory(
        "Hotel",
        Icons.Filled.Hotel
    )
)

class GetBusinessCategoriesUseCase : GetBusinessCategories {
    override fun invoke(): List<BusinessCategory> {
        return businessCategories
    }
}