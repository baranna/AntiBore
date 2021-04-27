package hu.mobillab.antibore.mock

import hu.mobillab.antibore.network.OccupationApi
import hu.mobillab.antibore.network.dto.OccupationDto
import retrofit2.Call
import java.math.BigDecimal

class MockOccupationApi : OccupationApi {

    override suspend fun getActivity(
        key: String?,
        type: String?,
        participants: Int?,
        price: BigDecimal?,
        maxprice: String?
    ): OccupationDto = OccupationDto()

}