package eam.xagh.unilocal.contexts.shared.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Title(
    body: String,
    principal: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(bottom = 16.dp)
    ) {
        Text(
            text = body,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = principal,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}