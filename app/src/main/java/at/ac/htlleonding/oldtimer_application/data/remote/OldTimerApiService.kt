package at.ac.htlleonding.oldtimer_application.data.remote

import at.ac.htlleonding.oldtimer_application.data.model.OldTimer
import retrofit2.http.Body
import retrofit2.http.GET;
import retrofit2.http.POST

interface OldTimerApiService{
    @GET("oldtimers")
    suspend fun getOldTimers(): List<OldTimer>

    @POST("oldtimers")
    suspend fun postOldTimer(@Body oldTimer: OldTimer): OldTimer

}