package eam.xagh.unilocal.auth.presentation.register

import androidx.compose.ui.graphics.vector.ImageVector
import eam.xagh.unilocal.auth.presentation.states.EmailState
import eam.xagh.unilocal.auth.presentation.states.PasswordState
import eam.xagh.unilocal.core.domain.entities.City

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
    val isLoading: Boolean = true,
    val cities: List<City> = emptyList()
)

data class RegisterState(
    val nicknameState: NicknameState = NicknameState(),
    val emailState: EmailState = EmailState(),
    val passwordState: PasswordState = PasswordState(),
    val nameState: NameState = NameState(),
    val cityState: CityState = CityState(),
    val doingRegister: Boolean = false
)