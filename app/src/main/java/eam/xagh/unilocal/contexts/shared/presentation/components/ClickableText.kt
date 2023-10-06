package eam.xagh.unilocal.contexts.shared.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import eam.xagh.unilocal.utils.noRippleClickable

@Composable
fun ClickableText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    onClick: () -> Unit
) {
    Text(
        text = text,
        style = style.copy(fontWeight = FontWeight.Bold),
        textDecoration = TextDecoration.Underline,
        modifier = modifier.noRippleClickable { onClick() }
    )
}