package eam.xagh.unilocal.shared.domain.services

interface ThemePreferenceService {
    val isDarkMode: Boolean
    fun setTheme(toDarkTheme: Boolean)
}