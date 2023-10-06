package eam.xagh.unilocal.presentation.base

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import eam.xagh.unilocal.presentation.NavigationScreen

val baseScreens = listOf(BaseScreens.Map)

sealed class BaseScreens(
    override val route: String,
    override val component: @Composable (NavController) -> Unit
) : NavigationScreen {
    object Map : BaseScreens("map", { MapScreen(it) })
}
