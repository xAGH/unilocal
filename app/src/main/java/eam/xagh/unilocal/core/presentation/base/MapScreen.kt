package eam.xagh.unilocal.core.presentation.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import eam.xagh.unilocal.shared.presentation.components.navigation_bar.ModeratorSection
import eam.xagh.unilocal.shared.presentation.components.navigation_bar.UserSection
import eam.xagh.unilocal.shared.presentation.layouts.NavigationBarLayout
import eam.xagh.unilocal.core.presentation.base.components.SearchInput
import eam.xagh.unilocal.shared.presentation.authentication.AuthenticationViewModel
import eam.xagh.unilocal.shared.presentation.components.BusinessCategories
import eam.xagh.unilocal.shared.presentation.theme.Shape

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MapScreen(navController: NavController) {
    val viewModel: MapViewModel = hiltViewModel()
    val authenticationViewModel: AuthenticationViewModel = hiltViewModel()
    val userIsModerator = authenticationViewModel.state.user?.isModerator

    NavigationBarLayout(
        navController = navController,
        section = if (userIsModerator == true) ModeratorSection.Map else UserSection.Map,
        isModerator = userIsModerator == true,
        isAuthenticated = authenticationViewModel.state.isAuthenticated,
        topBar = {
            Column {
                SearchInput(Modifier.padding(40.dp, 20.dp))
                Column(
                    modifier = Modifier
                        .padding(10.dp, 0.dp)
                        .align(Alignment.End)
                        .offset(x = 0.dp, y = 0.dp)
                        .clip(Shape.RoundedLarge)
                        .background(MaterialTheme.colorScheme.background)
                        .padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) { BusinessCategories(viewModel.businessCategories) }
            }
        },
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current
        val focusManager = LocalFocusManager.current
        val singapore = LatLng(1.35, 103.87)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(singapore, 10f)
        }
        GoogleMap(
            modifier = Modifier
                .fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(
                mapToolbarEnabled = false,
                compassEnabled = true,
                zoomControlsEnabled = false
            ),
            properties = MapProperties(
                mapType = MapType.NORMAL
            ),
            onMapClick = {
                keyboardController?.hide()
                focusManager.clearFocus()
            })
    }
}