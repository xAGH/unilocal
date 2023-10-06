package eam.xagh.unilocal.contexts.profile.presentation.profile

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import eam.xagh.unilocal.contexts.shared.presentation.theme.ThemeViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val themeViewModel: ThemeViewModel, private val application: Application
) : ViewModel() {

    var state by mutableStateOf(ProfileState(themeViewModel.state.isDarkMode))
     private set

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.ChangeTheme -> {
                themeViewModel.changeTheme(event.toDarkMode)
                state = state.copy(
                    isDarkMode = themeViewModel.state.isDarkMode
                )
            }
        }
    }
}