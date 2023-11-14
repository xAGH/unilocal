package eam.xagh.unilocal.business.presentation.components

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

@Composable
fun BusinessImage(imageUrl: String, imageSize: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(imageSize.dp)
    ) {
        SubcomposeAsyncImage(
            model = imageUrl,
            loading = {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            },
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun BusinessImage(imageUrl: Uri?, imageSize: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(imageSize.dp)
    ) {
        SubcomposeAsyncImage(
            model = imageUrl,
            loading = {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            },
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
    }
}