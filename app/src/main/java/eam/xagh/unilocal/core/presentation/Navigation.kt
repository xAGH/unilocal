package eam.xagh.unilocal.core.presentation

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eam.xagh.unilocal.auth.presentation.authScreens
import eam.xagh.unilocal.business.presentation.BusinessScreens
import eam.xagh.unilocal.business.presentation.businessScreens
import eam.xagh.unilocal.core.presentation.base.BaseScreens
import eam.xagh.unilocal.profile.presentation.profileScreens
import eam.xagh.unilocal.core.presentation.base.baseScreens

interface NavigationScreen {
    val route: String
    val component: @Composable (NavController) -> Unit
}

fun concatenate(vararg lists: List<NavigationScreen>) = listOf(*lists).flatten()

val screens = concatenate(
    baseScreens,
    authScreens,
    profileScreens,
    businessScreens
)

@Composable
fun UnilocalAppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = BaseScreens.Map.route
//        startDestination = BusinessScreens.CreateSchedule.route
    ) {
        screens.forEach { screen ->
            composable(
                route = screen.route,
            ) { screen.component(navController) }
        }
    }

    fun logBackStack(controller: NavController) {
        val backStack = StringBuilder("Back Stack: ")

        controller.backQueue.forEach { entry ->
            backStack.append(entry.destination.route).append(" -> ")
        }

        Log.d("BackStackLog", backStack.toString())
    }

    navController.addOnDestinationChangedListener { controller, _, _ ->
        logBackStack(controller)
    }
}