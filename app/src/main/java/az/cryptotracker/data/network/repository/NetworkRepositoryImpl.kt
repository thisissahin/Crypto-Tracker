package az.cryptotracker.data.network.repository

import az.cryptotracker.data.base.BaseNetworkRepository
import az.cryptotracker.data.network.source.ApiService
import az.cryptotracker.domain.model.response.RateResponse
import az.cryptotracker.domain.repository.NetworkRepository
import az.cryptotracker.presentation.util.ResultWrapper
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : BaseNetworkRepository(), NetworkRepository {

    override suspend fun getExchangeRates(): ResultWrapper<Response<RateResponse>> {
        return handleNetwork { apiService.getExchangeRates() }
    }

}