package eam.xagh.unilocal.shared.presentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import eam.xagh.unilocal.R

@Composable
fun LoadingIcon(
    animate: Boolean,
    modifier: Modifier = Modifier,
    imageVector: ImageVector? = null,
    color: Color? = null,
) {
    val transition = rememberInfiniteTransition(label = "Rotate infinite")
    val alpha by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(700, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "Rotate infinite"
    )

    Icon(
        imageVector = imageVector?: ImageVector.vectorResource(R.drawable.loader),
        contentDescription = null,
        tint = color?: LocalContentColor.current,
        modifier = if (animate) modifier.rotate(alpha) else modifier
    )

}