package eam.xagh.unilocal.shared.presentation.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import eam.xagh.unilocal.shared.domain.services.ThemePreferenceService
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val themePreference: ThemePreferenceService
): ViewModel() {

    var state by mutableStateOf(ThemeState(themePreference.isDarkMode))
        private set

    fun changeTheme(toDarkTheme: Boolean) {
        themePreference.setTheme(toDarkTheme);
        state = state.copy(
            isDarkMode = toDarkTheme
        )
    }
}