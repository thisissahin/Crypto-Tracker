package az.cryptotracker.domain.repository

import az.cryptotracker.domain.model.response.RateResponse
import az.cryptotracker.presentation.util.ResultWrapper
import retrofit2.Response

interface NetworkRepository {
    suspend fun getExchangeRates(): ResultWrapper<Response<RateResponse>>
}