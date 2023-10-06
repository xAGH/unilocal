package eam.xagh.unilocal.contexts.shared.domain.providers

interface ThemePreference {
    val isDarkMode: Boolean
    fun setTheme(toDarkTheme: Boolean)
}