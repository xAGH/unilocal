package eam.xagh.unilocal.auth.presentation.states

data class NicknameOrEmailState(
    val isValid: Boolean = false,
    val error: String? = null
)
