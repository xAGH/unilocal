package eam.xagh.unilocal.contexts.shared.presentation.layouts

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.navigation.NavController
import eam.xagh.unilocal.contexts.shared.presentation.components.navigation_bar.NavigationBar
import eam.xagh.unilocal.contexts.shared.presentation.components.navigation_bar.Section
import eam.xagh.unilocal.contexts.shared.presentation.components.navigation_bar.UserSection
import kotlin.math.abs

data class GoOnSwipe(
    val left: Section? = null,
    val right: Section? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationBarLayout(
    navController: NavController,
    section: Section,
    isModerator: Boolean,
    modifier: Modifier = Modifier,
    goOnSwipe: GoOnSwipe = GoOnSwipe(),
    topBar: (@Composable () -> Unit) = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        containerColor = Color.Transparent,
        topBar = topBar,
        bottomBar = {
            NavigationBar(
                navController = navController, activeSection = section, isModerator = isModerator, goOnLeftSwipe = UserSection.Map
            )
        },
        modifier = modifier.pointerInput(Unit) {
            detectDragGestures { _, dragAmount ->
                val (x, y) = dragAmount
                if(abs(x) > abs(y)){
                    when {
                        x > 50 -> goOnSwipe.left?.onClick?.let { it(navController) }
                        x < 50 -> goOnSwipe.right?.onClick?.let { it(navController) }
                    }
                }
            }
        }
    ) {
        content()
    }
}