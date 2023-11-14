package eam.xagh.unilocal.shared.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import eam.xagh.unilocal.business.domain.entities.BusinessCategory
import eam.xagh.unilocal.core.presentation.utils.noRippleClickable

@Composable
fun BusinessCategories(businessCategories: List<BusinessCategory>) {

    repeat(businessCategories.size) {
        var active by remember { mutableStateOf(true) }
        Icon(
            imageVector = businessCategories[it].icon,
            contentDescription = businessCategories[it].categoryName,
            tint = if (active) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .size(26.dp)
                .noRippleClickable { active = !active }
        )

    }
}