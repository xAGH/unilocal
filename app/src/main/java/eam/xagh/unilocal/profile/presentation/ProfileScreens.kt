package eam.xagh.unilocal.profile.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import eam.xagh.unilocal.core.presentation.NavigationScreen
import eam.xagh.unilocal.profile.presentation.my_profile.MyProfileScreen

val profileScreens = listOf(
    ProfileScreens.Profile
)

sealed class ProfileScreens(
    override val route: String,
    override val component: @Composable (NavController) -> Unit
) : NavigationScreen {
    object Profile : ProfileScreens("profile", { MyProfileScreen(it) })
}