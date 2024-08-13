package android.dev.cryptotrackerapp.data

import android.dev.cryptotrackerapp.data.model.CoinDetailsDTO
import android.dev.cryptotrackerapp.data.model.CoinModelDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoApi {

    @GET("coins/markets")
    suspend fun getCoinsMarkets(
        @Query("vs_currency") currency: String = "usd",
        @Query("page") page: Int = 1,
        @Query("per_page") numCoinsPerPage: Int = 20,
    ): Response<List<CoinModelDTO>>

    @GET("coins/{coin_id}")
    suspend fun getCoinDetails(
        @Path("coin_id") coinId: String,
    ) : Response<CoinDetailsDTO>
}