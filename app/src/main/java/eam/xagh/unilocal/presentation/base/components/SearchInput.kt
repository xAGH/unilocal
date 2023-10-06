package eam.xagh.unilocal.presentation.base.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import eam.xagh.unilocal.R
import eam.xagh.unilocal.contexts.shared.presentation.components.Input
import eam.xagh.unilocal.utils.noRippleClickable

@Composable
fun SearchInput(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier){
        Input(
            placeholder = stringResource(id = R.string.search),
            icon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.noRippleClickable {  }
                )
            },
            backgroundColor = MaterialTheme.colorScheme.background,
            required = false
        ) {

        }
    }
}