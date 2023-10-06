package eam.xagh.unilocal.contexts.profile.presentation.profile

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
import eam.xagh.unilocal.contexts.auth.presentation.AuthScreens
import eam.xagh.unilocal.contexts.profile.presentation.profile.components.GoToSectionButton
import eam.xagh.unilocal.contexts.profile.presentation.profile.components.ThemeIcon
import eam.xagh.unilocal.contexts.shared.presentation.components.Title
import eam.xagh.unilocal.contexts.shared.presentation.components.navigation_bar.UserSection
import eam.xagh.unilocal.contexts.shared.presentation.layouts.GoOnSwipe
import eam.xagh.unilocal.contexts.shared.presentation.layouts.IllustrationLayout
import eam.xagh.unilocal.contexts.shared.presentation.layouts.NavigationBarLayout
import eam.xagh.unilocal.utils.noRippleClickable

@Composable
private fun ProfileIcons(isDarkMode: Boolean, navController: NavController, onChangeTheme: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.6f),
            horizontalArrangement = Arrangement.SpaceEvenly
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
        Icon(
            imageVector = Icons.Filled.ExitToApp,
            contentDescription = "Exit",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(34.dp)
                .noRippleClickable { navController.navigate(AuthScreens.Register.route) }
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavController) {
    val viewModel: ProfileViewModel = hiltViewModel()
    val isDarkMode = viewModel.state.isDarkMode
    NavigationBarLayout(
        navController = navController,
        section = UserSection.Profile,
        isModerator = false,
        goOnSwipe = GoOnSwipe(
            left = UserSection.Map
        )
    ) {
        IllustrationLayout(illustrationName = "profile") {
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    Title(
                        body = stringResource(id = R.string.my),
                        principal = stringResource(id = R.string.profile),
                    )
                    ProfileIcons(isDarkMode, navController) {
                        viewModel.onEvent(
                            ProfileEvent.ChangeTheme(it)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
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