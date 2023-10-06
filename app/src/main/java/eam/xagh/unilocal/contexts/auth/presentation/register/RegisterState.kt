package eam.xagh.unilocal.contexts.auth.presentation.register

import androidx.compose.ui.graphics.vector.ImageVector
import eam.xagh.unilocal.contexts.auth.presentation.states.EmailState
import eam.xagh.unilocal.contexts.auth.presentation.states.PasswordState

data class NicknameState(
    val isValid: Boolean = false,
    val error: String? = null,
    val icon: ImageVector? = null
)

data class NameState(
    val isValid: Boolean = false,
    val error: String? = null
)

data class CityState(
    val cities: List<String> = emptyList(),
    val error: String = ""
)

data class RegisterState(
    val nicknameState: NicknameState = NicknameState(),
    val emailState: EmailState = EmailState(),
    val passwordState: PasswordState = PasswordState(),
    val nameState: NameState = NameState(),
    val cityState: CityState = CityState()
)