package eam.xagh.unilocal.shared.presentation.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eam.xagh.unilocal.shared.presentation.theme.Shape
import eam.xagh.unilocal.core.presentation.utils.noRippleClickable
import eam.xagh.unilocal.shared.presentation.components.Text

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TitleLayout(
    bodyTitle: String,
    featuredTitle: String,
    onReturnButtonClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .focusable()
            .noRippleClickable {
                keyboardController?.hide()
                focusManager.clearFocus()
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = "Return button",
                modifier = Modifier
                    .size(54.dp)
                    .noRippleClickable { onReturnButtonClick() },
                tint = MaterialTheme.colorScheme.primary
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = bodyTitle,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = featuredTitle,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 38.sp
                    ),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(Shape.RoundedLargeTop)
                .background(MaterialTheme.colorScheme.secondary)
                .padding(40.dp, 20.dp, 40.dp, 40.dp)
        ) {
            content()
        }
    }
}