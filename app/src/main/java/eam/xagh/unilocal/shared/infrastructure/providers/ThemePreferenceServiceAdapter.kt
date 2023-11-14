package eam.xagh.unilocal.shared.infrastructure.providers

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import eam.xagh.unilocal.shared.domain.services.ThemePreferenceService

class ThemePreferenceServiceAdapter constructor (private val context: Context) : ThemePreferenceService {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("ApplicationPreferences", Context.MODE_PRIVATE)

    private val darkThemeKey = "IS_DARK_THEME"

    override fun setTheme(toDarkTheme: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(darkThemeKey, toDarkTheme)
        editor.apply()
    }

    override val isDarkMode get(): Boolean {
        val uiMode = context.resources.configuration.uiMode
        val default: Boolean = when (uiMode.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> true
            else -> false
        }
        return sharedPreferences.getBoolean(darkThemeKey, default)
    }
}