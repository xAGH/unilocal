package eam.xagh.unilocal.core.presentation.base

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import eam.xagh.unilocal.business.domain.usecases.list.GetBusinessCategories
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    getBusinessCategories: GetBusinessCategories
) : ViewModel() {
    val businessCategories = getBusinessCategories()
}