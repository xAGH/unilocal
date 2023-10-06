package eam.xagh.unilocal.contexts.shared.presentation.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    background = Black800,
    primary = Purple500,
    secondary = Gray800,
    error = Red700,
    onPrimary = White800,
    tertiary = Green500,
    scrim = Pink100,
    inversePrimary = Yellow600,
    surface = Gray400,
    surfaceTint = GrayBlue400,
    outline = GrayBlue100
)

private val LightColorScheme = lightColorScheme(
    background = White800,
    primary = Blue500,
    secondary = Blue50,
    error = Red700,
    onPrimary = Black800,
    tertiary = Green500,
    scrim = Pink100,
    inversePrimary = Yellow600,
    surface = White800,
    surfaceTint = GrayBlue400,
    outline = GrayBlue100
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}