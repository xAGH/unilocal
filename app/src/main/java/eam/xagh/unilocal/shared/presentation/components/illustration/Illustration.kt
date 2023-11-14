package eam.xagh.unilocal.shared.presentation.components.illustration

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun Illustration(
    illustrationName: String,
    modifier: Modifier = Modifier
) {
    val viewModel: IllustrationViewModel = hiltViewModel();
    val id = viewModel.getIllustrationIdWithTheme(illustrationName)
    Image(
        painter = painterResource(id = id),
        contentDescription = null,
        modifier = modifier
    )
}