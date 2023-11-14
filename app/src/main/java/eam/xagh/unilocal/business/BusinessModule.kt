package eam.xagh.unilocal.business

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eam.xagh.unilocal.business.application.usecases.create.CreateBusinessUseCase
import eam.xagh.unilocal.business.application.usecases.create.ValidateBusinessDescriptionUseCase
import eam.xagh.unilocal.business.application.usecases.create.ValidateBusinessNameUseCase
import eam.xagh.unilocal.business.application.usecases.list.GetBusinessCategoriesUseCase
import eam.xagh.unilocal.business.application.usecases.list.GetBusinessUseCase
import eam.xagh.unilocal.business.domain.repositories.BusinessRepository
import eam.xagh.unilocal.business.domain.usecases.create.CreateBusiness
import eam.xagh.unilocal.business.domain.usecases.create.ValidateBusinessDescription
import eam.xagh.unilocal.business.domain.usecases.create.ValidateBusinessName
import eam.xagh.unilocal.business.domain.usecases.list.GetBusiness
import eam.xagh.unilocal.business.domain.usecases.list.GetBusinessCategories
import eam.xagh.unilocal.business.infrastructure.repositories.BusinessRepositoryAdapter
import eam.xagh.unilocal.business.presentation.create.BusinessCreateViewModel
import eam.xagh.unilocal.business.presentation.list.BusinessListViewModel
import eam.xagh.unilocal.shared.presentation.authentication.AuthenticationViewModel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BusinessModule {

    @Provides
    @Singleton
    fun providesValidateBusinessName(): ValidateBusinessName {
        return ValidateBusinessNameUseCase()
    }

    @Provides
    @Singleton
    fun providesValidateBusinessDescription(): ValidateBusinessDescription {
        return ValidateBusinessDescriptionUseCase()
    }

    @Provides
    @Singleton
    fun providesGetBusiness(): GetBusiness {
        return GetBusinessUseCase()
    }

    @Provides
    @Singleton
    fun providesBusinessRepository(): BusinessRepository {
        return BusinessRepositoryAdapter()
    }

    @Provides
    @Singleton
    fun providesGetBusinessCategories(): GetBusinessCategories {
        return GetBusinessCategoriesUseCase()
    }

    @Provides
    fun providesBusinessListViewModel(
        authenticationViewModel: AuthenticationViewModel,
        getBusiness: GetBusiness,
        businessRepository: BusinessRepository
    ): BusinessListViewModel {
        return BusinessListViewModel(
            authenticationViewModel, getBusiness, businessRepository
        )
    }

    @Provides
    @Singleton
    fun providesCreateBusiness(): CreateBusiness {
        return CreateBusinessUseCase()
    }

    @Provides
    @Singleton
    fun providesBusinessCreateDataViewModel(
        application: Application,
        validateBusinessName: ValidateBusinessName,
        validateBusinessDescription: ValidateBusinessDescription,
        createBusiness: CreateBusiness,
        authenticationViewModel: AuthenticationViewModel,
        businessRepository: BusinessRepository,
        getBusinessCategories: GetBusinessCategories
    ): BusinessCreateViewModel {
        return BusinessCreateViewModel(
            application,
            validateBusinessName,
            validateBusinessDescription,
            createBusiness,
            authenticationViewModel,
            businessRepository,
            getBusinessCategories
        )
    }
}
