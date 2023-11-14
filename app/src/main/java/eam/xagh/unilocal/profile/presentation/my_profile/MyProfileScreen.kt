package eam.xagh.unilocal.profile.presentation.my_profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.DesignServices
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Reviews
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import eam.xagh.unilocal.R
import eam.xagh.unilocal.core.presentation.base.BaseScreens
import eam.xagh.unilocal.profile.components.GoToSectionButton
import eam.xagh.unilocal.profile.components.ThemeIcon
import eam.xagh.unilocal.shared.presentation.components.Title
import eam.xagh.unilocal.shared.presentation.components.navigation_bar.UserSection
import eam.xagh.unilocal.shared.presentation.layouts.GoOnSwipe
import eam.xagh.unilocal.shared.presentation.layouts.IllustrationLayout
import eam.xagh.unilocal.shared.presentation.layouts.NavigationBarLayout
import eam.xagh.unilocal.core.presentation.utils.noRippleClickable
import eam.xagh.unilocal.shared.presentation.authentication.AuthenticationViewModel
import eam.xagh.unilocal.shared.presentation.components.navigation_bar.ModeratorSection

@Composable
private fun MyProfileIcons(
    isDarkMode: Boolean, onCloseSession: () -> Unit, onChangeTheme: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.6f), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ThemeIcon(
                isDark = false,
                isDarkMode = !isDarkMode,
            ) { onChangeTheme(false) }
            ThemeIcon(
                isDark = true,
                isDarkMode = isDarkMode,
            ) { onChangeTheme(true) }
        }
        Icon(imageVector = Icons.Filled.ExitToApp,
            contentDescription = "Exit",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(34.dp)
                .noRippleClickable { onCloseSession() })
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyProfileScreen(navController: NavController) {
    val viewModel: MyProfileViewModel = hiltViewModel()
    val authenticationViewModel: AuthenticationViewModel = hiltViewModel()
    val isDarkMode = viewModel.state.isDarkMode
    NavigationBarLayout(
        navController = navController,
        section = if (authenticationViewModel.state.isModerator) ModeratorSection.Profile else UserSection.Profile,
        isModerator = authenticationViewModel.state.isModerator,
        isAuthenticated = authenticationViewModel.state.isAuthenticated,
        goOnSwipe = GoOnSwipe(
            left = UserSection.Map
        )
    ) {
        IllustrationLayout(
            illustrationName = "profile",
            onReturnButtonClick = {
                navController.popBackStack()
            }
        ) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    Title(
                        body = stringResource(id = R.string.my),
                        principal = stringResource(id = R.string.profile),
                    )
                    MyProfileIcons(isDarkMode = isDarkMode, onCloseSession = {
                        viewModel.onEvent(MyProfileEvent.CloseSession)
                        navController.navigate(BaseScreens.Map.route) {
                            popUpTo(BaseScreens.Map.route) { inclusive = true }
                        }
                    }) {
                        viewModel.onEvent(
                            MyProfileEvent.ChangeTheme(it)
                        )
                    }
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    GoToSectionButton(icon = Icons.Filled.Reviews, text = "My Reviews") {}
                    GoToSectionButton(icon = Icons.Filled.DesignServices, text = "Edit Info") {}
                    GoToSectionButton(icon = Icons.Filled.Bookmark, text = "Favorites") {}
                }
            }
        }
    }
}