package hu.mobillab.antibore.interactors

import hu.mobillab.antibore.model.Category
import hu.mobillab.antibore.model.Occupation
import hu.mobillab.antibore.network.OccupationApi
import hu.mobillab.antibore.network.dto.OccupationDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import hu.mobillab.antibore.repository.OccupationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class OccupationInteractor @Inject constructor(var occupationApi: OccupationApi, var occupationRepository: OccupationRepository) {

    fun getOccupations(callback: (List<Occupation?>) -> Unit) {
        // TODO: get stored occupations and get new ones
        CoroutineScope(Dispatchers.IO).launch {
            occupationRepository.getAllOccupations()
        }

        getOccupation() {
            callback(listOf(it))
        }
    }

    fun getOccupation(key: String = "", callback: (Occupation?) -> Unit) {
        occupationApi.getActivity(key).enqueue(
            object : Callback<OccupationDto> {
                override fun onResponse(
                    call: Call<OccupationDto>,
                    response: Response<OccupationDto>
                ) {
                    val body = response.body()
                    callback(
                        if (body !== null)
                            mapOccupation(body)
                        else null
                    )
                }

                override fun onFailure(call: Call<OccupationDto>, t: Throwable) {
                    callback(null)
                }

            }
        )
    }

    fun mapOccupation(dto: OccupationDto) = Occupation(
        dto.key ?: "",
        dto.activity ?: "",
        dto.accessibility?.toDouble() ?: 0.0,
        Category.fromValue(dto.type?.value ?: "") ?: Category.DIY,
        dto.participants?.toInt() ?: 0,
        dto.price?.toDouble() ?: 0.0,
        dto.link ?: ""
    )

}