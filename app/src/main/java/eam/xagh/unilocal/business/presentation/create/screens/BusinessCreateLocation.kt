package eam.xagh.unilocal.business.presentation.create.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import eam.xagh.unilocal.R
import eam.xagh.unilocal.business.presentation.BusinessScreens
import eam.xagh.unilocal.business.presentation.create.BusinessCreateEvent
import eam.xagh.unilocal.business.presentation.create.BusinessCreateViewModel
import eam.xagh.unilocal.shared.presentation.components.LargeButton
import eam.xagh.unilocal.shared.presentation.components.Text
import eam.xagh.unilocal.shared.presentation.components.Title
import eam.xagh.unilocal.shared.presentation.layouts.IllustrationLayout

@Composable
fun BusinessCreateLocation(navController: NavController) {
    val viewModel: BusinessCreateViewModel = hiltViewModel()
    val marker: MutableState<LatLng?> = remember { mutableStateOf(viewModel.state.businessLocation) }

    IllustrationLayout(
        illustrationName = "create_business_location",
        onReturnButtonClick = { navController.popBackStack() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Title(
                body = stringResource(id = R.string.create_business),
                principal = stringResource(id = R.string.location),
            )
            Text(
                text = stringResource(id = R.string.select_location),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
            ) {
                val singapore = LatLng(1.35, 103.87)
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(singapore, 10f)
                }
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
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
                        marker.value = it
                    }
                ) {
                    if (marker.value != null) {
                        Marker(
                            MarkerState(marker.value!!)
                        )
                    }
                }
            }
            LargeButton(
                text = stringResource(id = R.string.continue_text),
                modifier = Modifier.padding(top = 16.dp),
                enabled = marker.value != null
            ) {
                viewModel.onEvent(
                    BusinessCreateEvent.SaveLocation(
                        marker.value!!
                    )
                )
                val route = BusinessScreens.CreateSchedule.route
                navController.navigate(route) {
                    popUpTo(route) { inclusive = false }
                }
            }
        }
    }
}