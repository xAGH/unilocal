package eam.xagh.unilocal.core

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eam.xagh.unilocal.business.domain.usecases.list.GetBusinessCategories
import eam.xagh.unilocal.core.presentation.base.MapViewModel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Provides
    @Singleton
    fun providesMapViewModel(
        getBusinessCategories: GetBusinessCategories
    ): MapViewModel {
        return MapViewModel(getBusinessCategories)
    }
}