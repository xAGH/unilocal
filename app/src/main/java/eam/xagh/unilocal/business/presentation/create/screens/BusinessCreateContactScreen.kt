package eam.xagh.unilocal.business.presentation.create.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import eam.xagh.unilocal.R
import eam.xagh.unilocal.business.presentation.BusinessScreens
import eam.xagh.unilocal.business.presentation.create.BusinessCreateEvent
import eam.xagh.unilocal.business.presentation.create.BusinessCreateViewModel
import eam.xagh.unilocal.business.presentation.create.components.BusinessImages
import eam.xagh.unilocal.business.presentation.create.components.BusinessPhones
import eam.xagh.unilocal.core.presentation.utils.noRippleClickable
import eam.xagh.unilocal.shared.presentation.components.LargeButton
import eam.xagh.unilocal.shared.presentation.components.Text
import eam.xagh.unilocal.shared.presentation.components.Title
import eam.xagh.unilocal.shared.presentation.layouts.IllustrationLayout

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BusinessCreateContactScreen(navController: NavController) {
    val viewModel: BusinessCreateViewModel = hiltViewModel()
    val state = viewModel.state
    var phones by remember { mutableStateOf(state.businessPhones) }
    var images by remember { mutableStateOf(state.businessImages) }
    var phonesInvalid by remember { mutableStateOf(false) }
    var imagesInvalid by remember { mutableStateOf(false) }

    if (phonesInvalid) {
        AlertDialog(
            icon = { Icon(Icons.Filled.Info, null) },
            title = {
                Text(
                    text = stringResource(id = R.string.invalid_data),
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            text = { Text(text = stringResource(id = R.string.phone_error)) },
            onDismissRequest = { phonesInvalid = false },
            confirmButton = {},
        )
    }

    if (imagesInvalid) {
        AlertDialog(
            icon = { Icon(Icons.Filled.Info, null) },
            title = {
                Text(
                    text = stringResource(id = R.string.invalid_data),
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            text = { Text(text = stringResource(id = R.string.images_error)) },
            onDismissRequest = { imagesInvalid = false },
            confirmButton = {},
        )
    }

    IllustrationLayout(illustrationName = "create_business_contact",
        onReturnButtonClick = { navController.popBackStack() }) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Title(
                body = stringResource(id = R.string.create_business),
                principal = stringResource(id = R.string.contact),
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    BusinessPhones(phones) { newPhones -> phones = newPhones }
                    BusinessImages(images) { newImage -> images = newImage }
                }
                LargeButton(
                    text = stringResource(id = R.string.continue_text),
                    modifier = Modifier.padding(top = 16.dp),
                ) {
                    if (!phones.all { phone -> phone.length == 10 || phone.length == 7 }) {
                        phonesInvalid = true
                    } else if (images.size < 2) imagesInvalid = true
                    else {
                        viewModel.onEvent(BusinessCreateEvent.SaveContact(phones, images))
                        val route = BusinessScreens.CreateLocation.route
                        navController.navigate(route) {
                            popUpTo(route) { inclusive = false }
                        }
                    }
                }
            }
        }
    }
}