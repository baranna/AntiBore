package hu.mobillab.antibore.network
//retrofit2
import hu.mobillab.antibore.network.dto.OccupationDto
import retrofit2.http.*
import java.math.BigDecimal

interface OccupationApi {
    /**
     * Your GET endpoint
     * Get random occupation object
     * @param key  A unique numeric id (optional)
     * @param type Find a random activity with a given type (optional)
     * @param participants Find a random activity with a given number of participants (optional)
     * @param price Find an activity with a specified price (optional)
     * @param maxprice Find an event with a specified max price (optional)
     * @return Call&lt;Occupation&gt;
     */
    @GET("activity")
    fun getActivity(
            @retrofit2.http.Query("key") key: String? = "", @retrofit2.http.Query("type") type: String? = "", @retrofit2.http.Query("participants") participants: Int? = null, @retrofit2.http.Query("price") price: BigDecimal? = null, @retrofit2.http.Query("maxprice") maxprice: String? = ""
    ): retrofit2.Call<OccupationDto>
}