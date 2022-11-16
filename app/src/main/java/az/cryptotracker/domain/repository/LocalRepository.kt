package az.cryptotracker.domain.repository

import az.cryptotracker.domain.model.CryptoRange
import az.cryptotracker.domain.model.CryptoRate
import az.cryptotracker.domain.model.enums.enums.CryptoType

interface LocalRepository {
    suspend fun insertRateHistory(rate: CryptoRate)
    suspend fun getRateHistoryByType(type: String): List<CryptoRate>?

    suspend fun getCheckRateByType(type: CryptoType): CryptoRange?
    suspend fun setCheckRate(rate: CryptoRange)

}