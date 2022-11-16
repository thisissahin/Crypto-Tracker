package az.cryptotracker.presentation.manager

import az.cryptotracker.domain.model.response.RateResponse
import az.cryptotracker.presentation.util.ResultWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RatesManager  @Inject constructor (){
    val rateResponse = MutableStateFlow<ResultWrapper<Response<RateResponse>>?>(null)
}