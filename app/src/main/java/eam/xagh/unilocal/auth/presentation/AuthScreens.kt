package eam.xagh.unilocal.auth.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import eam.xagh.unilocal.auth.presentation.login.LoginScreen
import eam.xagh.unilocal.auth.presentation.password_recovery.PasswordRecoveryScreen
import eam.xagh.unilocal.auth.presentation.register.RegisterScreen
import eam.xagh.unilocal.core.presentation.NavigationScreen

val authScreens = listOf(
    AuthScreens.Login,
    AuthScreens.Register,
    AuthScreens.PasswordRecovery
)

sealed class AuthScreens(
    override val route: String,
    override val component: @Composable (NavController) -> Unit
): NavigationScreen {
    object Login: AuthScreens("login", { LoginScreen(it) })
    object Register: AuthScreens("register", { RegisterScreen(it) })
    object PasswordRecovery: AuthScreens("password_recovery", { PasswordRecoveryScreen(it) })
}