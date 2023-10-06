package eam.xagh.unilocal.contexts.shared.presentation.components.navigation_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import eam.xagh.unilocal.contexts.profile.presentation.ProfileScreens
import eam.xagh.unilocal.presentation.base.BaseScreens

interface Section {
    val icon: ImageVector
    val description: String
    val onClick: (NavController) -> Unit
}

sealed class ModeratorSection(
    override val icon: ImageVector,
    override val description: String,
    override val onClick: (NavController) -> Unit
) : Section {
    // object Business: ModeratorSection(Icons.Filled.Business, "Business", { it.navigate("") })
    object Map : ModeratorSection(
        icon = Icons.Filled.Map,
        description = "Map",
        onClick = { it.navigate(BaseScreens.Map.route) }
    )

    object PendingBusiness : ModeratorSection(Icons.Filled.List, "List", { it.navigate("") })
    object Historic : ModeratorSection(Icons.Filled.Description, "Description", { it.navigate("") })
    object Profile : ModeratorSection(Icons.Filled.Person, "Profile", { it.navigate("") })
}

sealed class UserSection(
    override val icon: ImageVector,
    override val description: String,
    override val onClick: (NavController) -> Unit
) : Section {
    object Business : UserSection(
        icon = Icons.Filled.Business,
        description = "Business",
        onClick = { it.navigate("") { popUpTo(0) } }
    )

    object Map : UserSection(
        icon = Icons.Filled.Map,
        description = "Map",
        onClick = {
            val route = BaseScreens.Map.route
            it.navigate(route) { popUpTo(route) { inclusive = true } }
        }
    )

    object Profile : UserSection(
        icon = Icons.Filled.Person,
        description = "Profile",
        onClick = {
            val route = ProfileScreens.Profile.route
            it.navigate(route) { popUpTo(route) { inclusive = true } }
        }
    )
}

val userSections = listOf(
    UserSection.Business,
    UserSection.Map,
    UserSection.Profile
)
val moderatorSections = listOf(
    ModeratorSection.Map,
    ModeratorSection.PendingBusiness,
    ModeratorSection.Historic,
    ModeratorSection.Profile
)