package eam.xagh.unilocal.contexts.shared.presentation.components.business_categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.Museum
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import eam.xagh.unilocal.contexts.shared.presentation.theme.Shape

@Composable
private fun Content() {
    val icons = Icons.Filled
    var active1 by remember { mutableStateOf(true) }
    var active2 by remember { mutableStateOf(true) }
    var active3 by remember { mutableStateOf(true) }
    var active4 by remember { mutableStateOf(true) }
    var active5 by remember { mutableStateOf(true) }
    BusinessCategory(
        icon = icons.Restaurant,
        description = "Restaurant",
        active = active1
    ) { active1 = !active1 }
    BusinessCategory(
        icon = icons.Coffee,
        description = "Coffe",
        active = active2
    ) { active2 = !active2 }
    BusinessCategory(
        icon = icons.Fastfood,
        description = "Fastfood",
        active = active3
    ) { active3 = !active3 }
    BusinessCategory(
        icon = icons.Museum,
        description = "Museum",
        active = active4
    ) { active4 = !active4 }
    BusinessCategory(
        icon = icons.Hotel,
        description = "Hotel",
        active = active5
    ) { active5 = !active5 }
}

@Composable
fun BusinessCategories(
    modifier: Modifier = Modifier,
    inColumn: Boolean
) {
    if (inColumn) {
        Column(
            modifier = modifier
                .offset(x = 0.dp, y = 0.dp)
                .clip(Shape.RoundedLarge)
                .background(MaterialTheme.colorScheme.background)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) { Content() }
    }
    else {
        Row(
            modifier = modifier
                .offset(x = 0.dp, y = 0.dp)
                .clip(Shape.RoundedLarge)
                .background(MaterialTheme.colorScheme.background)
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) { Content() }
    }
}