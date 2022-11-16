package az.cryptotracker.domain.use_case

import az.cryptotracker.domain.model.response.RateResponse
import az.cryptotracker.domain.repository.NetworkRepository
import az.cryptotracker.presentation.util.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class GetExchangeRates @Inject constructor(
    private val networkRepository: NetworkRepository
) {
    operator fun invoke(): Flow<ResultWrapper<Response<RateResponse>>> = flow {
        emit(ResultWrapper.Loading())
        emit(networkRepository.getExchangeRates())
    }
}