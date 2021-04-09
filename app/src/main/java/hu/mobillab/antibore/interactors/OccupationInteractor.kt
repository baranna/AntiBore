package hu.mobillab.antibore.interactors

import hu.mobillab.antibore.model.Occupation
import hu.mobillab.antibore.network.OccupationApi
import javax.inject.Inject

class OccupationInteractor @Inject constructor(var occupationApi: OccupationApi) {

    fun getOccupations(): List<Occupation> = occupationApi.getOccupations()

    fun getOccupation(key: String): Occupation = occupationApi.getOccupation(key);

}