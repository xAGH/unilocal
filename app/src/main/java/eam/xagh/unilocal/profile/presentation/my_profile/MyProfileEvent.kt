package eam.xagh.unilocal.profile.presentation.my_profile

sealed interface MyProfileEvent {
    data class ChangeTheme(val toDarkMode: Boolean): MyProfileEvent
    object CloseSession: MyProfileEvent
}