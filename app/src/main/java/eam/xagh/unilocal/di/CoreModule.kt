package eam.xagh.unilocal.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eam.xagh.unilocal.contexts.auth.infrastructure.repositories.AuthenticationRepositoryAdapter
import eam.xagh.unilocal.presentation.base.MapViewModel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Provides
    @Singleton
    fun providesMapViewModel(
        authenticationRepositoryAdapter: AuthenticationRepositoryAdapter,
        application: Application
    ): MapViewModel {
        return MapViewModel(authenticationRepositoryAdapter, application)
    }
}