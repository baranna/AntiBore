package hu.mobillab.antibore.interactors

import hu.mobillab.antibore.model.Occupation
import hu.mobillab.antibore.network.OccupationApi
import hu.mobillab.antibore.repository.OccupationRepository
import javax.inject.Inject

class OccupationInteractor @Inject constructor(var occupationApi: OccupationApi, var occupationRepository: OccupationRepository) {

    suspend fun getOccupations(): List<Occupation> {
        occupationRepository.getAllOccupations()
        return occupationApi.getOccupations()
    }

    fun getOccupation(key: String): Occupation = occupationApi.getOccupation(key);

}