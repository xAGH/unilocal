package eam.xagh.unilocal.contexts.profile.presentation.profile

sealed interface ProfileEvent {
    data class ChangeTheme(val toDarkMode: Boolean): ProfileEvent
}