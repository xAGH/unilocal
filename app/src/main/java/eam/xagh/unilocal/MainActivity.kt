package eam.xagh.unilocal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import eam.xagh.unilocal.contexts.shared.presentation.theme.AppTheme
import eam.xagh.unilocal.contexts.shared.presentation.theme.ThemeViewModel
import eam.xagh.unilocal.presentation.UnilocalAppNavigation

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val themeViewModel: ThemeViewModel by viewModels()
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme(themeViewModel.state.isDarkMode) {
                UnilocalAppNavigation()
            }
        }
    }
}