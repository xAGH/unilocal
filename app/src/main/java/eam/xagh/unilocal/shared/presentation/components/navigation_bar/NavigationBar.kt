package eam.xagh.unilocal.shared.presentation.components.navigation_bar

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eam.xagh.unilocal.auth.presentation.AuthScreens
import eam.xagh.unilocal.shared.presentation.theme.Shape
import eam.xagh.unilocal.core.presentation.utils.noRippleClickable

@Composable
private fun Navigation(
    navController: NavController,
    sections: Iterable<Section>,
    activeSection: Section,
    isAuthenticated: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(Shape.RoundedLarge)
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        sections.forEach { section ->
            val currentSection = section == activeSection
            val backgroundColor =
                if (currentSection) MaterialTheme.colorScheme.secondary else Color.Transparent
            val iconTint =
                if (currentSection) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(backgroundColor)
                    .padding(4.dp)
            ) {
                Icon(
                    imageVector = section.icon,
                    contentDescription = section.description,
                    tint = iconTint,
                    modifier = Modifier
                        .size(34.dp)
                        .noRippleClickable {
                            if (section.protected) {
                                if (!isAuthenticated) {
                                    val route = AuthScreens.Login.route
                                    navController.navigate(route) { popUpTo(route) { inclusive = true } }
                                    return@noRippleClickable
                                }
                            }
                            section.onClick(navController)
                        }
                )
            }
        }
    }
}

@Composable
fun NavigationBar(
    navController: NavController,
    activeSection: Section,
    isAuthenticated: Boolean,
    isModerator: Boolean,
) {

    Box(
        modifier = Modifier
            .padding(40.dp, 20.dp)
    ) {
        Navigation(
            navController,
            if (isModerator) moderatorSections else userSections,
            activeSection,
            isAuthenticated
        )
    }
}