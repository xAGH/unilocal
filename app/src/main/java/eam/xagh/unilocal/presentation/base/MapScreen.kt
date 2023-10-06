package eam.xagh.unilocal.presentation.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import eam.xagh.unilocal.contexts.shared.presentation.components.navigation_bar.ModeratorSection
import eam.xagh.unilocal.contexts.shared.presentation.components.navigation_bar.UserSection
import eam.xagh.unilocal.contexts.shared.presentation.layouts.NavigationBarLayout
import eam.xagh.unilocal.contexts.shared.presentation.components.business_categories.BusinessCategories
import eam.xagh.unilocal.presentation.base.components.SearchInput

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MapScreen(navController: NavController) {
    val authenticated = false;
    NavigationBarLayout(
        navController = navController,
        section = if (authenticated) ModeratorSection.Map else UserSection.Map,
        isModerator = authenticated,
        topBar = {
            Column {
                SearchInput(Modifier.padding(40.dp, 20.dp))
                BusinessCategories(
                    Modifier
                        .padding(10.dp, 0.dp)
                        .align(Alignment.End), true
                )
            }
        },
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current
        val focusManager = LocalFocusManager.current
        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            uiSettings = MapUiSettings(
                mapToolbarEnabled = false,
                compassEnabled = false,
                zoomControlsEnabled = false
            ),
            onMapClick = {
                keyboardController?.hide()
                focusManager.clearFocus()
            })
    }
}