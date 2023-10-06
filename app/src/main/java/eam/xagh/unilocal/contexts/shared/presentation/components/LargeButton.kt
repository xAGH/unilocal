package eam.xagh.unilocal.contexts.shared.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import eam.xagh.unilocal.contexts.shared.presentation.theme.Shape

@Composable
fun LargeButton(
    text: String,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .clip(Shape.RoundedMedium)
            .height(50.dp),
        enabled = enabled
    ) {
        Text(
            text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}