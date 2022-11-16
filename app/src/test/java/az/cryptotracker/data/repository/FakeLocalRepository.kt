package az.cryptotracker.data.repository

import az.cryptotracker.domain.model.CryptoRange
import az.cryptotracker.domain.model.CryptoRate
import az.cryptotracker.domain.model.enums.enums.CryptoType
import az.cryptotracker.domain.repository.LocalRepository

class FakeLocalRepository : LocalRepository {

    private val rates = mutableListOf<CryptoRate>()

    lateinit var btcRange: CryptoRange
    lateinit var ethRange: CryptoRange
    lateinit var xrpRange: CryptoRange

    override suspend fun insertRateHistory(rate: CryptoRate) {
        rates.add(rate)
    }

    override suspend fun getRateHistoryByType(type: String): List<CryptoRate> {
        return rates.filter { it.cryptoName == type }
    }

    override suspend fun getCheckRangeByType(type: CryptoType): CryptoRange? {
        return when (type) {
            CryptoType.BTC -> btcRange
            CryptoType.ETH -> ethRange
            CryptoType.XRP -> xrpRange
        }
    }

    override suspend fun setCheckRange(range: CryptoRange) {
        when (CryptoType.valueOf(range.cryptoName)) {
            CryptoType.BTC -> btcRange = range
            CryptoType.ETH -> ethRange = range
            CryptoType.XRP -> xrpRange = range
        }
    }
}