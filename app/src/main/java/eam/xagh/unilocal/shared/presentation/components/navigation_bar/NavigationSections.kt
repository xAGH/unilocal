package eam.xagh.unilocal.shared.presentation.components.navigation_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import eam.xagh.unilocal.business.presentation.BusinessScreens
import eam.xagh.unilocal.profile.presentation.ProfileScreens
import eam.xagh.unilocal.core.presentation.base.BaseScreens

interface Section {
    val icon: ImageVector
    val description: String
    val onClick: (NavController) -> Unit
    val protected: Boolean
}

sealed class ModeratorSection(
    override val icon: ImageVector,
    override val description: String,
    override val onClick: (NavController) -> Unit,
    override val protected: Boolean
) : Section {
    // object Business: ModeratorSection(Icons.Filled.Business, "Business", { it.navigate("") })
    object Map : ModeratorSection(
        icon = Icons.Filled.Map,
        description = "Map",
        onClick = { it.navigate(BaseScreens.Map.route) },
        protected = false
    )

    object PendingBusiness : ModeratorSection(
        icon = Icons.Filled.List,
        description = "List",
        onClick = { /*it.navigate("")*/ },
        protected = true
    )

    object Historic : ModeratorSection(
        icon = Icons.Filled.Description,
        description = "Description",
        onClick = { it.navigate("") },
        protected = true
    )

    object Profile : ModeratorSection(
        icon = Icons.Filled.Person,
        description = "Profile",
        onClick = {
            val route = ProfileScreens.Profile.route
            it.navigate(route) { popUpTo(route) { inclusive = true } }
        },
        protected = true
    )
}

sealed class UserSection(
    override val icon: ImageVector,
    override val description: String,
    override val onClick: (NavController) -> Unit,
    override val protected: Boolean
) : Section {
    object Business : UserSection(
        icon = Icons.Filled.Business,
        description = "Business",
        onClick = {
            val route = BusinessScreens.List.route
            it.navigate(route) { popUpTo(route) { inclusive = true } }
        },
        protected = true
    )

    object Map : UserSection(
        icon = Icons.Filled.Map,
        description = "Map",
        onClick = {
            val route = BaseScreens.Map.route
            it.navigate(route) { popUpTo(route) { inclusive = true } }
        },
        protected = false
    )

    object Profile : UserSection(
        icon = Icons.Filled.Person,
        description = "Profile",
        onClick = {
            val route = ProfileScreens.Profile.route
            it.navigate(route) { popUpTo(route) { inclusive = true } }
        },
        protected = true
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