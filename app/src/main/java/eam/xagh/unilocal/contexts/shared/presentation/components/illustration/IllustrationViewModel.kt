package eam.xagh.unilocal.contexts.shared.presentation.components.illustration

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import eam.xagh.unilocal.contexts.shared.presentation.theme.ThemeViewModel
import javax.inject.Inject

@HiltViewModel
class IllustrationViewModel @Inject constructor(
    private val themeViewModel: ThemeViewModel, private val application: Application
) : ViewModel() {

    var state by mutableStateOf(IllustrationState())
        private set

    @SuppressLint("DiscouragedApi")
    fun getIllustrationIdWithTheme(illustrationName: String): Int {
        val name = illustrationName + (if (themeViewModel.state.isDarkMode) "_dark"
        else "_light")
        val illustrationId = application.resources.getIdentifier(
            name, "drawable", application.packageName
        )
        state = state.copy(illustrationId = illustrationId)
        return illustrationId
    }
}