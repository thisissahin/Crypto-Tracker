package az.cryptotracker.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.room.Room
import az.cryptotracker.data.local.source.RatesDao
import az.cryptotracker.data.local.source.RatesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun providesRatesDao(database: RatesDatabase): RatesDao =
        database.rateDao

    @Provides
    @Singleton
    fun providesRatesDatabase(application: Application): RatesDatabase =
        Room.databaseBuilder(application, RatesDatabase::class.java, RatesDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()


    @Singleton
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences("crypto_tracker_pref", Context.MODE_PRIVATE)
    }
}