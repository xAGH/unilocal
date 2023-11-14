package eam.xagh.unilocal.business.presentation.create.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import eam.xagh.unilocal.R
import eam.xagh.unilocal.business.domain.entities.BusinessCategory
import eam.xagh.unilocal.core.presentation.utils.noRippleClickable
import eam.xagh.unilocal.shared.presentation.components.Text

@Composable
private fun RowBusinessCategories(
    businessCategories: List<BusinessCategory>,
    activeCategory: String,
    onClick: (selected: String) -> Unit
) {
    val status = mutableListOf<MutableState<Boolean>>()
    repeat(businessCategories.size) { index ->
        val selected =
            remember { mutableStateOf(businessCategories[index].categoryName == activeCategory) }
        status.add(selected)
        Icon(imageVector = businessCategories[index].icon,
            contentDescription = businessCategories[index].categoryName,
            tint = if (selected.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .size(26.dp)
                .noRippleClickable {
                    status.forEach { status ->
                        status.value = false
                        selected.value = true
                    }
                    onClick(businessCategories[index].categoryName)
                })
    }
}

@Composable
fun SelectCategory(
    selectedCategory: String,
    businessCategories: List<BusinessCategory>,
    onClick: (value: String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row {
            Text(
                text = stringResource(id = R.string.category),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
            )
            Text(
                text = ": $selectedCategory",
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(10.dp)
            ) {
                RowBusinessCategories(businessCategories, selectedCategory) { onClick(it) }
            }
        }
    }
}