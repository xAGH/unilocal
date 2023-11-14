package eam.xagh.unilocal.shared.presentation.authentication

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import eam.xagh.unilocal.auth.domain.entities.User
import eam.xagh.unilocal.shared.domain.services.AuthenticationService
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authenticationService: AuthenticationService,
) : ViewModel() {

    private fun reloadState() = AuthenticationState(
        isAuthenticated = authenticationService.isAuthenticated,
        isModerator = authenticationService.user?.isModerator ?: false,
        user = authenticationService.user,
    )


    var state by mutableStateOf(reloadState())
        private set

    fun login(user: User) {
        authenticationService.setUser(user)
        state = reloadState()
    }

    fun closeSession() {
        authenticationService.closeSession()
        state = reloadState()
    }

}