package eam.xagh.unilocal.auth.presentation.states

data class EmailState(
    val isValid: Boolean = false,
    val error: String? = null
)