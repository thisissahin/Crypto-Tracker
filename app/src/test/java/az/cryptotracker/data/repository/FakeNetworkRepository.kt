package az.cryptotracker.data.repository

import az.cryptotracker.domain.model.enums.enums.CryptoType
import az.cryptotracker.domain.model.response.CryptoDetail
import az.cryptotracker.domain.model.response.CryptoValue
import az.cryptotracker.domain.model.response.RateResponse
import az.cryptotracker.domain.repository.NetworkRepository
import az.cryptotracker.presentation.util.ResultWrapper
import retrofit2.Response

class FakeNetworkRepository : NetworkRepository {

    private val rateResponse = RateResponse(
        CryptoDetail(
            CryptoValue(1.0, CryptoType.BTC),
            CryptoValue(16.87, CryptoType.ETH),
            CryptoValue(2433.0, CryptoType.XRP),
        )
    )

    override suspend fun getExchangeRates(): ResultWrapper<Response<RateResponse>> {
        return ResultWrapper.Success(Response.success(rateResponse))
    }
}