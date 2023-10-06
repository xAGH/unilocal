package eam.xagh.unilocal.contexts.shared.presentation.components.business_categories

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import eam.xagh.unilocal.utils.noRippleClickable

@Composable
fun BusinessCategory(
    icon: ImageVector,
    description: String,
    active: Boolean,
    onClick: () -> Unit
) {
    val color = if(active) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
    Icon(
        imageVector = icon,
        contentDescription = description,
        tint = color,
        modifier = Modifier
            .size(26.dp)
            .noRippleClickable { onClick() }
    )
}