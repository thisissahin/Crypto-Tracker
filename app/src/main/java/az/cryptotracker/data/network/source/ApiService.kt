package az.cryptotracker.data.network.source

import az.cryptotracker.data.network.source.Endpoints.RATES
import az.cryptotracker.domain.model.response.RateResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(RATES)
    suspend fun getExchangeRates(): Response<RateResponse>

}