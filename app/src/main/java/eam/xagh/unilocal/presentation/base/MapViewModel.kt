package eam.xagh.unilocal.presentation.base

import android.app.Application
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import eam.xagh.unilocal.contexts.auth.infrastructure.repositories.AuthenticationRepositoryAdapter
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepositoryAdapter,
    private val application: Application
): ViewModel() {

}