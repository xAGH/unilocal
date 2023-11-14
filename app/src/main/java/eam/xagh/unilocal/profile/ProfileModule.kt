package eam.xagh.unilocal.profile

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eam.xagh.unilocal.profile.presentation.my_profile.MyProfileViewModel
import eam.xagh.unilocal.shared.presentation.authentication.AuthenticationViewModel
import eam.xagh.unilocal.shared.presentation.theme.ThemeViewModel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {

    @Provides
    @Singleton
    fun provideProfileViewModel(
        themeViewModel: ThemeViewModel,
        authenticationViewModel: AuthenticationViewModel
    ): MyProfileViewModel {
        return MyProfileViewModel(themeViewModel, authenticationViewModel)
    }

}