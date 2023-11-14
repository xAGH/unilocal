package eam.xagh.unilocal.auth

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eam.xagh.unilocal.auth.application.usecases.user.GetUserUseCase
import eam.xagh.unilocal.auth.application.usecases.user.LoginUserUseCase
import eam.xagh.unilocal.auth.application.usecases.user.RecoveryPasswordUseCase
import eam.xagh.unilocal.auth.application.usecases.user.RegisterUserUseCase
import eam.xagh.unilocal.auth.application.usecases.user.SendRecoveryCodeUseCase
import eam.xagh.unilocal.auth.application.usecases.validate.ValidateEmailUseCase
import eam.xagh.unilocal.auth.application.usecases.validate.ValidateNameUseCase
import eam.xagh.unilocal.auth.application.usecases.validate.ValidateNicknameUseCase
import eam.xagh.unilocal.auth.application.usecases.validate.ValidatePasswordUseCase
import eam.xagh.unilocal.auth.application.usecases.validate.ValidateRecoveryCodeUseCase
import eam.xagh.unilocal.auth.domain.repositories.UserRepository
import eam.xagh.unilocal.auth.domain.usecases.user.GetUser
import eam.xagh.unilocal.auth.domain.usecases.user.LoginUser
import eam.xagh.unilocal.auth.domain.usecases.user.RecoveryPassword
import eam.xagh.unilocal.auth.infrastructure.repositories.UserRepositoryAdapter
import eam.xagh.unilocal.auth.presentation.login.LoginViewModel
import eam.xagh.unilocal.auth.presentation.password_recovery.PasswordRecoveryViewModel
import eam.xagh.unilocal.auth.presentation.register.RegisterViewModel
import eam.xagh.unilocal.auth.domain.usecases.user.RegisterUser
import eam.xagh.unilocal.auth.domain.usecases.user.SendRecoveryCode
import eam.xagh.unilocal.auth.domain.usecases.validate.ValidateEmail
import eam.xagh.unilocal.auth.domain.usecases.validate.ValidateName
import eam.xagh.unilocal.auth.domain.usecases.validate.ValidateNickname
import eam.xagh.unilocal.auth.domain.usecases.validate.ValidatePassword
import eam.xagh.unilocal.auth.domain.usecases.validate.ValidateRecoveryCode
import eam.xagh.unilocal.core.domain.repositories.CityRepository
import eam.xagh.unilocal.core.infrastructure.repositories.CityRepositoryAdapter
import eam.xagh.unilocal.shared.presentation.authentication.AuthenticationViewModel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun providesValidateNickname(): ValidateNickname {
        return ValidateNicknameUseCase()
    }

    @Provides
    @Singleton
    fun providesValidatePassword(): ValidatePassword {
        return ValidatePasswordUseCase()
    }

    @Provides
    @Singleton
    fun providesValidateEmail(): ValidateEmail {
        return ValidateEmailUseCase()
    }

    @Provides
    @Singleton
    fun providesValidateName(): ValidateName {
        return ValidateNameUseCase()
    }

    @Provides
    @Singleton
    fun providesCityRepository(): CityRepository {
        return CityRepositoryAdapter()
    }

    @Provides
    @Singleton
    fun providesUserRepository(): UserRepository {
        return UserRepositoryAdapter()
    }

    @Provides
    @Singleton
    fun providesRegisterUser(): RegisterUser {
        return RegisterUserUseCase()
    }

    @Provides
    fun providesRegisterViewModel(
        validateEmail: ValidateEmail,
        validateNickname: ValidateNickname,
        validateName: ValidateName,
        validatePassword: ValidatePassword,
        cityRepository: CityRepository,
        registerUser: RegisterUser,
        userRepository: UserRepository,
        application: Application
    ): RegisterViewModel {
        return RegisterViewModel(
            validateEmail,
            validateNickname,
            validateName,
            validatePassword,
            cityRepository,
            registerUser,
            userRepository,
            application
        )
    }

    @Provides
    @Singleton
    fun providesLoginUser(): LoginUser {
        return LoginUserUseCase()
    }

    @Provides
    fun providesLoginViewModel(
        validateNickname: ValidateNickname,
        validatePassword: ValidatePassword,
        validateEmail: ValidateEmail,
        userRepository: UserRepository,
        loginUser: LoginUser,
        authenticationViewModel: AuthenticationViewModel,
        application: Application
    ): LoginViewModel {
        return LoginViewModel(
            validateNickname,
            validatePassword,
            validateEmail,
            userRepository,
            loginUser,
            authenticationViewModel,
            application
        )
    }

    @Provides
    @Singleton
    fun providesValidateRecoveryCode(): ValidateRecoveryCode {
        return ValidateRecoveryCodeUseCase()
    }

    @Provides
    @Singleton
    fun providesRecoveryPassword(): RecoveryPassword {
        return RecoveryPasswordUseCase()
    }

    @Provides
    @Singleton
    fun providesSendRecoveryCode(): SendRecoveryCode {
        return SendRecoveryCodeUseCase()
    }

    @Provides
    fun providesPasswordRecoveryViewModel(
        recoveryPassword: RecoveryPassword,
        sendRecoveryCode: SendRecoveryCode,
        validateEmail: ValidateEmail,
        validateNickname: ValidateNickname,
        userRepository: UserRepository,
        validatePassword: ValidatePassword,
        validateRecoveryCode: ValidateRecoveryCode,
        application: Application
    ): PasswordRecoveryViewModel {
        return PasswordRecoveryViewModel(
            recoveryPassword,
            sendRecoveryCode,
            validateEmail,
            validateNickname,
            userRepository,
            validatePassword,
            validateRecoveryCode,
            application
        )
    }
}