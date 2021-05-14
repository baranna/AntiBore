package hu.mobillab.antibore.interactors

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import hu.mobillab.antibore.model.Category
import hu.mobillab.antibore.model.Occupation
import hu.mobillab.antibore.network.OccupationApi
import hu.mobillab.antibore.network.dto.OccupationDto
import hu.mobillab.antibore.repository.OccupationRepository
import javax.inject.Inject

class OccupationInteractor @Inject constructor(
    var occupationApi: OccupationApi,
    var occupationRepository: OccupationRepository
) {

    suspend fun getOccupations(): List<Occupation> {
        val list = mutableListOf<Occupation>()
        for (i in 1..10) {
            list.add(mapOccupation(occupationApi.getActivity()))
        }
        return list;
    }

    suspend fun getOccupation(key: String = ""): Pair<Occupation, Boolean> {
        val storedOccupation = occupationRepository.getOccupation(key)
        if (storedOccupation != null) {
            return (storedOccupation to true);
        }

        Firebase.analytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
            param(FirebaseAnalytics.Param.CONTENT_TYPE, "occupation")
            param(FirebaseAnalytics.Param.ITEM_ID, key)
        }

        return (mapOccupation(occupationApi.getActivity(key)) to false)
    }

    suspend fun saveOccupation(occupation: Occupation) {
        occupationRepository.addOccupation(occupation)
    }

    suspend fun deleteOccupation(occupation: Occupation) {
        occupationRepository.deleteOccupation(occupation)
    }

    suspend fun getSavedOccupations(): List<Occupation> {
        return occupationRepository.getAllOccupations()
    }

    private fun mapOccupation(dto: OccupationDto) = Occupation(
        dto.key ?: "",
        dto.activity ?: "",
        dto.accessibility?.toDouble() ?: 0.0,
        Category.fromValue(dto.type?.value ?: "") ?: Category.DIY,
        dto.participants?.toInt() ?: 0,
        dto.price?.toDouble() ?: 0.0,
        dto.link ?: ""
    )

}