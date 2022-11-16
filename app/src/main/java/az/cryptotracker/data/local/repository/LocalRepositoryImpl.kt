package az.cryptotracker.data.local.repository

import android.content.SharedPreferences
import az.cryptotracker.data.local.source.RatesDao
import az.cryptotracker.domain.model.CryptoRange
import az.cryptotracker.domain.model.CryptoRate
import az.cryptotracker.domain.model.enums.enums.CryptoType
import az.cryptotracker.domain.repository.LocalRepository
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepositoryImpl @Inject constructor(
    private val ratesDao: RatesDao,
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : LocalRepository {

    override suspend fun insertRateHistory(rate: CryptoRate) {
        return ratesDao.insert(rate)
    }

    override suspend fun getRateHistoryByType(type: String): List<CryptoRate>? {
        return ratesDao.getRateHistoryByType(type)
    }

    override suspend fun getCheckRateByType(type: CryptoType): CryptoRange? {
        return gson.fromJson(sharedPreferences.getString(type.name, ""), CryptoRange::class.java)
    }

    override suspend fun setCheckRate(rate: CryptoRange) {
        sharedPreferences.edit().putString(rate.cryptoName, gson.toJson(rate)).apply()
    }


}