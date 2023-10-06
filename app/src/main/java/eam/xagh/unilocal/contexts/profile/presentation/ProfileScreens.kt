package eam.xagh.unilocal.contexts.profile.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import eam.xagh.unilocal.contexts.profile.presentation.profile.ProfileScreen
import eam.xagh.unilocal.presentation.NavigationScreen

val profileScreens = listOf(
    ProfileScreens.Profile
)

sealed class ProfileScreens(
    override val route: String,
    override val component: @Composable (NavController) -> Unit
) : NavigationScreen {
    object Profile : ProfileScreens("profile", { ProfileScreen(it) })
}