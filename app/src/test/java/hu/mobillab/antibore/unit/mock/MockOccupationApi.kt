package hu.mobillab.antibore.unit.mock

import hu.mobillab.antibore.network.OccupationApi
import hu.mobillab.antibore.network.dto.OccupationDto
import java.math.BigDecimal

open class MockOccupationApi : OccupationApi {

    override suspend fun getActivity(
        key: String?,
        type: String?,
        participants: Int?,
        price: BigDecimal?,
        maxprice: String?
    ): OccupationDto = occupation

    companion object {
        val occupation = OccupationDto().apply {
            key = "occupationkey"
            activity = "Test activity"
        }
    }

}