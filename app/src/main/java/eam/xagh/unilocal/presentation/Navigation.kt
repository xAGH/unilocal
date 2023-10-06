package eam.xagh.unilocal.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eam.xagh.unilocal.contexts.auth.presentation.authScreens
import eam.xagh.unilocal.contexts.profile.presentation.ProfileScreens
import eam.xagh.unilocal.contexts.profile.presentation.profileScreens
import eam.xagh.unilocal.presentation.base.baseScreens

interface NavigationScreen {
    val route: String
    val component: @Composable (NavController) -> Unit
}

fun concatenate(vararg lists: List<NavigationScreen>) = listOf(*lists).flatten()

val screens = concatenate(
    baseScreens,
    authScreens,
    profileScreens
)

@Composable
fun UnilocalAppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ProfileScreens.Profile.route
    ) {
        screens.forEach { screen ->
            composable(
                route = screen.route,
            ){ screen.component(navController) }
        }
    }
}