package eam.xagh.unilocal.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import eam.xagh.unilocal.shared.presentation.theme.Shape
import eam.xagh.unilocal.core.presentation.utils.noRippleClickable

@Composable
fun ThemeIcon(
    isDark: Boolean,
    isDarkMode: Boolean,
    onClick: () -> Unit
) {
    val icon = if(isDark) Icons.Filled.DarkMode else Icons.Filled.LightMode
    val description =  if(isDark) "DarkMode" else "LightMode"
    val bgBackground = if(isDarkMode) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background
    Box(
        modifier = Modifier
            .clip(Shape.RoundedSmall)
            .background(bgBackground)
            .padding(4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            modifier = Modifier
                .size(26.dp)
                .noRippleClickable { onClick() },
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}