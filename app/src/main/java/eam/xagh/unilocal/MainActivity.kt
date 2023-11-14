package eam.xagh.unilocal

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint
import eam.xagh.unilocal.shared.presentation.theme.AppTheme
import eam.xagh.unilocal.shared.presentation.theme.ThemeViewModel
import eam.xagh.unilocal.core.presentation.UnilocalAppNavigation

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        val themeViewModel: ThemeViewModel by viewModels()
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme(themeViewModel.state.isDarkMode) {
                UnilocalAppNavigation()
            }
        }
    }
}