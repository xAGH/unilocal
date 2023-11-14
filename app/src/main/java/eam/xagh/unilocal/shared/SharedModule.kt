package eam.xagh.unilocal.shared

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eam.xagh.unilocal.shared.domain.services.AuthenticationService
import eam.xagh.unilocal.shared.infrastructure.providers.ThemePreferenceServiceAdapter
import eam.xagh.unilocal.shared.domain.services.ThemePreferenceService
import eam.xagh.unilocal.shared.infrastructure.providers.AuthenticationServiceAdapter
import eam.xagh.unilocal.shared.presentation.authentication.AuthenticationViewModel
import eam.xagh.unilocal.shared.presentation.components.illustration.IllustrationViewModel
import eam.xagh.unilocal.shared.presentation.theme.ThemeViewModel
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SharedModule {
    @Provides
    @Singleton
    fun provideThemeViewModel(themePreferenceService: ThemePreferenceService): ThemeViewModel {
        return ThemeViewModel(themePreferenceService)
    }

    @Provides
    @Singleton
    fun provideAuthenticationViewModel(authenticationService: AuthenticationService): AuthenticationViewModel {
        return AuthenticationViewModel(authenticationService)
    }

    @Provides
    @Singleton
    fun provideAuthenticationService(application: Application): AuthenticationService {
        return AuthenticationServiceAdapter(application)
    }

    @Provides
    @Singleton
    fun provideThemePreferenceServiceAdapter(application: Application): ThemePreferenceService {
        return ThemePreferenceServiceAdapter(application)
    }

    @Provides
    @Singleton
    fun provideIllustrationViewModel(
        themeViewModel: ThemeViewModel,
        application: Application
    ): IllustrationViewModel {
        return IllustrationViewModel(themeViewModel, application)
    }
}