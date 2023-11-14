package eam.xagh.unilocal.business.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import eam.xagh.unilocal.R
import eam.xagh.unilocal.business.domain.entities.Business
import eam.xagh.unilocal.business.presentation.BusinessScreens
import eam.xagh.unilocal.business.presentation.components.BusinessImage
import eam.xagh.unilocal.core.presentation.utils.noRippleClickable
import eam.xagh.unilocal.shared.presentation.authentication.AuthenticationViewModel
import eam.xagh.unilocal.shared.presentation.components.Text
import eam.xagh.unilocal.shared.presentation.components.illustration.Illustration
import eam.xagh.unilocal.shared.presentation.components.navigation_bar.UserSection
import eam.xagh.unilocal.shared.presentation.layouts.GoOnSwipe
import eam.xagh.unilocal.shared.presentation.layouts.NavigationBarLayout
import eam.xagh.unilocal.shared.presentation.layouts.TitleLayout
import eam.xagh.unilocal.shared.presentation.theme.Shape

@Composable
private fun ApprovedBusiness(ratings: String, comments: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
            Text(ratings)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Comment,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
            Text(comments)
        }
    }
}

@Composable
private fun BusinessCard(business: Business) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(Shape.RoundedSmall)
            .background(MaterialTheme.colorScheme.background)
            .noRippleClickable {

            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BusinessImage(business.images[0], 100)
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = business.name,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    if (business.approved) {
                        ApprovedBusiness(
                            ratings = business.ratings.toString(),
                            comments = business.comments.size.toString()
                        )
                    } else {
                        Text(
                            text = stringResource(id = R.string.pendind_approval),
                            color = MaterialTheme.colorScheme.surface
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun NoBusiness() {
    Text(
        text = stringResource(id = R.string.no_business),
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center
    )
    Illustration(illustrationName = "empty_list")
}

@Composable
private fun BusinessList(business: Map<String, List<Business>>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.85f)
    ) {
        items(business.toList()) { (key, values) ->
            Text(
                key,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                values.forEach { BusinessCard(it) }
            }
            Divider(
                modifier = Modifier.padding(32.dp, 16.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.background
            )
        }
    }
}

@Composable
fun BusinessListScreen(navController: NavController) {
    val viewModel: BusinessListViewModel = hiltViewModel()
    val authenticationViewModel: AuthenticationViewModel = hiltViewModel()
    var fetchingBusiness by remember { mutableStateOf(true) }
    var business: Map<String, List<Business>> by remember { mutableStateOf(emptyMap()) }

    LaunchedEffect(Unit) {
        business = viewModel.getBusiness()
        fetchingBusiness = false
    }

    NavigationBarLayout(
        navController = navController,
        section = UserSection.Business,
        isModerator = authenticationViewModel.state.isModerator,
        isAuthenticated = authenticationViewModel.state.isAuthenticated,
        goOnSwipe = GoOnSwipe(
            right = UserSection.Map
        )
    ) {

        TitleLayout(
            bodyTitle = stringResource(id = R.string.my),
            featuredTitle = stringResource(id = R.string.business)
        ) {
            if (fetchingBusiness) {
                Dialog(
                    onDismissRequest = {},
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp),
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.94f)
                        .padding(0.dp, 16.dp)
                ) {
                    if (business.isEmpty()) NoBusiness() else BusinessList(business)
                    FloatingActionButton(
                        onClick = {
                            val route = BusinessScreens.CreateData.route
                            navController.navigate(route) {
                                popUpTo(route) { inclusive = false }
                            }
                        },
                        shape = CircleShape,
                        containerColor = MaterialTheme.colorScheme.background
                    ) {
                        Icon(
                            Icons.Filled.Add,
                            "Large floating action button",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}
