package az.cryptotracker.data.local.source

import androidx.room.Database
import androidx.room.RoomDatabase
import az.cryptotracker.domain.model.CryptoRate

@Database(
    entities = [CryptoRate::class],
    version = 1,
    exportSchema = false
)
abstract class RatesDatabase : RoomDatabase() {

    abstract val rateDao: RatesDao

    companion object {
        const val DATABASE_NAME = "rates_db"
    }
}