package eam.xagh.unilocal.business.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import eam.xagh.unilocal.business.presentation.create.screens.BusinessCreateContactScreen
import eam.xagh.unilocal.business.presentation.create.screens.BusinessCreateDataScreen
import eam.xagh.unilocal.business.presentation.create.screens.BusinessCreateLocation
import eam.xagh.unilocal.business.presentation.create.screens.BusinessCreateScheduleScreen
import eam.xagh.unilocal.business.presentation.list.BusinessListScreen
import eam.xagh.unilocal.core.presentation.NavigationScreen

val businessScreens = listOf(
    BusinessScreens.List,
    BusinessScreens.CreateData,
    BusinessScreens.CreateContact,
    BusinessScreens.CreateLocation,
    BusinessScreens.CreateSchedule
)

sealed class BusinessScreens(
    override val route: String, override val component: @Composable (NavController) -> Unit
) : NavigationScreen {
    object List : BusinessScreens("my-business", { BusinessListScreen(it) })
    object CreateData : BusinessScreens("create-business-data", { BusinessCreateDataScreen(it) })
    object CreateContact :
        BusinessScreens("create-business-contact", { BusinessCreateContactScreen(it) })

    object CreateLocation :
        BusinessScreens("create-business-location", { BusinessCreateLocation(it) })

    object CreateSchedule :
        BusinessScreens("create-business-schedule", { BusinessCreateScheduleScreen(it) })

}