package az.cryptotracker.data.local.source

import androidx.room.*
import az.cryptotracker.domain.model.CryptoRate

@Dao
interface RatesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cryptoRange: CryptoRate)

    @Query("SELECT * FROM crypto_table WHERE Name LIKE :cryptoName ORDER BY id DESC")
    fun getRateHistoryByType(cryptoName: String): List<CryptoRate>?

}