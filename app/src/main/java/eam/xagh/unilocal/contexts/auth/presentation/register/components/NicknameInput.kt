package eam.xagh.unilocal.contexts.auth.presentation.register.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import eam.xagh.unilocal.R
import eam.xagh.unilocal.contexts.auth.presentation.register.RegisterEvent
import eam.xagh.unilocal.contexts.auth.presentation.register.RegisterViewModel
import eam.xagh.unilocal.contexts.shared.presentation.components.Input

@Composable
private fun NicknameIcon(value: String, nicknameValid: Boolean, nicknameIcon: ImageVector?) {
    val transition = rememberInfiniteTransition(label = "Rotate infinite")
    val alpha by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(700, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "Rotate infinite"
    )
    if (!(value.isEmpty() || value.isBlank())) {
        Icon(
            imageVector = nicknameIcon ?: ImageVector.vectorResource(R.drawable.loader),
            contentDescription = "NicknameIsValid",
            tint = if (nicknameValid && nicknameIcon != null) MaterialTheme.colorScheme.tertiary else LocalContentColor.current,
            modifier = if (nicknameIcon == null) Modifier.rotate(alpha) else Modifier
        )
    }
}

@Composable
fun NicknameInput(
    viewModel: RegisterViewModel,
    nickname: MutableState<String>,
    onDone: () -> Unit
) {
    val nicknameValid = viewModel.state.nicknameState.isValid
    val nicknameIcon = viewModel.state.nicknameState.icon
    Input(
        value = nickname.value,
        label = stringResource(id = R.string.nickname),
        placeholder = stringResource(id = R.string.test_nickname),
        keyboardOptions = KeyboardOptions(
            autoCorrect = false,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { onDone() }
        ),
        extraInvalidReason = if (nicknameIcon == null) true else !nicknameValid,
        icon = { NicknameIcon(nickname.value, nicknameValid, nicknameIcon) },
        errorMessage = viewModel.state.nicknameState.error
    ) {
        viewModel.onEvent(RegisterEvent.ValidateNickname(it))
        nickname.value = it
    }
}