package eam.xagh.unilocal.contexts.shared.application.providers

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import eam.xagh.unilocal.contexts.shared.domain.providers.ThemePreference

class ThemePreferenceAdapter constructor (private val context: Context) : ThemePreference {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("ApplicationPreferernces", Context.MODE_PRIVATE)

    private val darkThemeKey = "IS_DARK_THEME"

    override fun setTheme(toDarkTheme: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(darkThemeKey, toDarkTheme)
        editor.apply()
    }

    override val isDarkMode get(): Boolean {
        val uimode = context.resources.configuration.uiMode
        val default: Boolean = when (uimode.and(Configuration.UI_MODE_NIGHT_MASK)) {
            Configuration.UI_MODE_NIGHT_YES -> true
            else -> false
        }
        return sharedPreferences.getBoolean(darkThemeKey, default)
    }
}