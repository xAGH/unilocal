package eam.xagh.unilocal.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eam.xagh.unilocal.contexts.shared.application.providers.ThemePreferenceAdapter
import eam.xagh.unilocal.contexts.shared.domain.providers.ThemePreference
import eam.xagh.unilocal.contexts.shared.presentation.components.illustration.IllustrationViewModel
import eam.xagh.unilocal.contexts.shared.presentation.theme.ThemeViewModel
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SharedModule {
    @Provides
    @Singleton
    fun provideThemeViewModel(themePreferenceAdapter: ThemePreference): ThemeViewModel {
        return ThemeViewModel(themePreferenceAdapter)
    }

    @Provides
    @Singleton
    fun provideThemePreference(application: Application): ThemePreference {
        return ThemePreferenceAdapter(application)
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