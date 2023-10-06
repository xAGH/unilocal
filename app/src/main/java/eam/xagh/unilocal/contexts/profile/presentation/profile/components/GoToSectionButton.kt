package eam.xagh.unilocal.contexts.profile.presentation.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import eam.xagh.unilocal.contexts.shared.presentation.components.Text
import eam.xagh.unilocal.contexts.shared.presentation.theme.Shape
import eam.xagh.unilocal.utils.noRippleClickable

@Composable
fun GoToSectionButton(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    val modifier = Modifier
        .noRippleClickable {
            onClick()
        }
    Column(
        modifier = modifier
            .size(140.dp, 90.dp)
            .clip(Shape.RoundedMedium)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = modifier.weight(1f)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = modifier.fillMaxSize()
                )
            }
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center)
            )
        }
    }

}