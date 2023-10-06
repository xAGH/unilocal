package eam.xagh.unilocal.contexts.shared.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

class Shape {
    companion object {
        val RoundedSmall = RoundedCornerShape(8.dp)
        val RoundedSmallTop = RoundedCornerShape(8.dp, 8.dp)
        val RoundedSmallBottom = RoundedCornerShape(0.dp, 0.dp, 8.dp, 8.dp)
        val RoundedSmallLeft = RoundedCornerShape(8.dp, 0.dp, 0.dp, 8.dp)
        val RoundedSmallRight = RoundedCornerShape(0.dp, 8.dp, 8.dp, 0.dp)

        val RoundedMedium= RoundedCornerShape(12.dp)
        val RoundedMediumTop = RoundedCornerShape(12.dp, 12.dp)
        val RoundedMediumBottom = RoundedCornerShape(0.dp, 0.dp, 12.dp, 12.dp)
        val RoundedMediumLeft = RoundedCornerShape(12.dp, 0.dp, 0.dp, 12.dp)
        val RoundedMediumRight = RoundedCornerShape(0.dp, 12.dp, 12.dp, 0.dp)

        val Rounded = RoundedCornerShape(16.dp)
        val RoundedTop = RoundedCornerShape(16.dp, 16.dp)
        val RoundedBottom = RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp)
        val RoundedLeft = RoundedCornerShape(16.dp, 0.dp, 0.dp, 16.dp)
        val RoundedRight = RoundedCornerShape(0.dp, 16.dp, 16.dp, 0.dp)

        val RoundedLarge = RoundedCornerShape(24.dp)
        val RoundedLargeTop = RoundedCornerShape(24.dp, 24.dp)
        val RoundedLargeBottom = RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp)
        val RoundedLargeLeft = RoundedCornerShape(24.dp, 0.dp, 0.dp, 24.dp)
        val RoundedLargeRight = RoundedCornerShape(0.dp, 24.dp, 24.dp, 0.dp)
    }
}