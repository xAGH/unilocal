package eam.xagh.unilocal.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eam.xagh.unilocal.contexts.profile.presentation.profile.ProfileViewModel
import eam.xagh.unilocal.contexts.shared.presentation.theme.ThemeViewModel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {

    @Provides
    @Singleton
    fun provideProfileViewModel(
        themeViewModel: ThemeViewModel,
        application: Application
    ): ProfileViewModel {
        return ProfileViewModel(themeViewModel, application)
    }

}