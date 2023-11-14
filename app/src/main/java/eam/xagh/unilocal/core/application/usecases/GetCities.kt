package eam.xagh.unilocal.core.application.usecases

import eam.xagh.unilocal.core.domain.entities.City
import eam.xagh.unilocal.core.domain.repositories.CityRepository
import kotlinx.coroutines.delay

suspend fun getCitiesUseCase(repository: CityRepository): List<City> {
    delay(5000)
    return repository.getCities()
}