package az.cryptotracker.presentation.ui.fragment.RateHistoryFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.cryptotracker.domain.model.CryptoRate
import az.cryptotracker.domain.model.enums.enums.CryptoType
import az.cryptotracker.domain.use_case.GetRateHistoryByType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RateHistoryViewModel @Inject constructor(
    private val getRateHistoryByType: GetRateHistoryByType
) : ViewModel() {

    private val _history = MutableStateFlow<List<CryptoRate>?>(emptyList())
    val history = _history.asStateFlow()

    fun getHistoryByType(type: CryptoType) = viewModelScope.launch(Dispatchers.IO) {
        _history.emit(getRateHistoryByType.invoke(type))
    }
}