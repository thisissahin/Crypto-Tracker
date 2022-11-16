package az.cryptotracker.presentation.service

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import az.cryptotracker.domain.model.CryptoRange
import az.cryptotracker.domain.model.enums.enums.CryptoType
import az.cryptotracker.domain.model.response.CryptoValue
import az.cryptotracker.domain.use_case.GetCheckRateByType
import az.cryptotracker.domain.use_case.GetExchangeRates
import az.cryptotracker.domain.use_case.InsertRateHistory
import az.cryptotracker.presentation.manager.RatesManager
import az.cryptotracker.presentation.util.AppNotificationManager
import az.cryptotracker.presentation.util.ResultWrapper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FetchRatesService : LifecycleService() {

    @Inject
    lateinit var appNotificationManager: AppNotificationManager

    @Inject
    lateinit var getExchangeRates: GetExchangeRates

    @Inject
    lateinit var getCheckRateByType: GetCheckRateByType

    @Inject
    lateinit var insertRateHistory: InsertRateHistory

    @Inject
    lateinit var ratesManager: RatesManager

    override fun onCreate() {
        super.onCreate()
        startForeground(
            AppNotificationManager.FETCH_RATES_NOTIFICATION_ID,
            appNotificationManager.getFetchServiceNotification()
        )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        lifecycleScope.launch {
            while (true) {
                val btcRange = getCheckRateByType.invoke(CryptoType.BTC)
                val ethRange = getCheckRateByType.invoke(CryptoType.ETH)
                val xrpRange = getCheckRateByType.invoke(CryptoType.XRP)

                getExchangeRates.invoke().collectLatest {
                    ratesManager.rateResponse.emit(it)
                    when (it) {
                        is ResultWrapper.Success -> {
                            it.data?.body()?.getList()?.forEach {
                                when (it.unit) {
                                    CryptoType.BTC -> compereRates(btcRange, it)
                                    CryptoType.ETH -> compereRates(ethRange, it)
                                    CryptoType.XRP -> compereRates(xrpRange, it)
                                }
                            }
                        }
                        else -> {}
                    }
                }
                delay(10000)
            }
        }

        return START_STICKY
    }

    private fun compereRates(savedRange: CryptoRange?, current: CryptoValue) {
        lifecycleScope.launch {
            savedRange?.let {
                if (current.value > savedRange.maxValue) {
                    insertRateHistory.invoke(current.mapToCryptoRate())
                    appNotificationManager.showPushBuilder(
                        AppNotificationManager.NOTIFY_RATE_CHANGES_NOTIFICATION_ID,
                        title = savedRange.cryptoName,
                        description = "is greater than saved value",
                        AppNotificationManager.NOTIFY_RATE_CHANGES_CHANNEL
                    )
                } else if (current.value < savedRange.minValue) {
                    insertRateHistory.invoke(current.mapToCryptoRate())
                    appNotificationManager.showPushBuilder(
                        AppNotificationManager.NOTIFY_RATE_CHANGES_NOTIFICATION_ID,
                        title = savedRange.cryptoName,
                        description = "is smaller than saved value",
                        AppNotificationManager.NOTIFY_RATE_CHANGES_CHANNEL
                    )
                }
            }
        }

    }

}