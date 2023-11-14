package eam.xagh.unilocal.auth.presentation.states

data class PasswordState(
    val isValid: Boolean = false,
    val error: String? = null,
    val isVisible: Boolean = false
)