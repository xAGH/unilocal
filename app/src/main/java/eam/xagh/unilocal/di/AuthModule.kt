package eam.xagh.unilocal.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eam.xagh.unilocal.contexts.auth.infrastructure.repositories.AuthenticationRepositoryAdapter
import eam.xagh.unilocal.contexts.auth.presentation.login.LoginViewModel
import eam.xagh.unilocal.contexts.auth.presentation.password_recovery.PasswordRecoveryViewModel
import eam.xagh.unilocal.contexts.auth.presentation.register.RegisterViewModel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun providesAuthenticationRepositoryAdapter(): AuthenticationRepositoryAdapter {
        return AuthenticationRepositoryAdapter()
    }

    @Provides
    @Singleton
    fun providesRegisterViewModel(
        authenticationRepositoryAdapter: AuthenticationRepositoryAdapter,
        application: Application
    ): RegisterViewModel {
        return RegisterViewModel(authenticationRepositoryAdapter, application)
    }

    @Provides
    @Singleton
    fun providesLoginViewModel(
        authenticationRepositoryAdapter: AuthenticationRepositoryAdapter,
        application: Application
    ): LoginViewModel {
        return LoginViewModel(authenticationRepositoryAdapter, application)
    }

    @Provides
    @Singleton
    fun providesPasswordRecoveryViewModel(
        authenticationRepositoryAdapter: AuthenticationRepositoryAdapter,
        application: Application
    ): PasswordRecoveryViewModel {
        return PasswordRecoveryViewModel(authenticationRepositoryAdapter, application)
    }
}