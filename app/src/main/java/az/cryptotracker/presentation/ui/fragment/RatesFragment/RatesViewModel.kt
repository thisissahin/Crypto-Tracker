package az.cryptotracker.presentation.ui.fragment.RatesFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.cryptotracker.domain.model.response.RateResponse
import az.cryptotracker.domain.use_case.GetExchangeRates
import az.cryptotracker.presentation.manager.RatesManager
import az.cryptotracker.presentation.util.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RatesViewModel @Inject constructor(
    ratesManager: RatesManager
) : ViewModel() {

    val exchangeRates = ratesManager.rateResponse.asStateFlow()

}