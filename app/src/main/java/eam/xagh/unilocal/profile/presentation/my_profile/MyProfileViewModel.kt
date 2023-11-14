package eam.xagh.unilocal.profile.presentation.my_profile

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import eam.xagh.unilocal.shared.presentation.authentication.AuthenticationViewModel
import eam.xagh.unilocal.shared.presentation.theme.ThemeViewModel
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val themeViewModel: ThemeViewModel,
    private val authenticationViewModel: AuthenticationViewModel
) : ViewModel() {

    var state by mutableStateOf(
        MyProfileState(
            themeViewModel.state.isDarkMode,
        )
    )
        private set

    fun onEvent(event: MyProfileEvent) {
        when (event) {
            is MyProfileEvent.ChangeTheme -> {
                themeViewModel.changeTheme(event.toDarkMode)
                state = state.copy(
                    isDarkMode = themeViewModel.state.isDarkMode
                )
            }

            MyProfileEvent.CloseSession -> authenticationViewModel.closeSession()
        }
    }
}