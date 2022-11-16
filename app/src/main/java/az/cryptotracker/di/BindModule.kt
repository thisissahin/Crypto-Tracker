package az.cryptotracker.di

import az.cryptotracker.data.local.repository.LocalRepositoryImpl
import az.cryptotracker.data.network.repository.NetworkRepositoryImpl
import az.cryptotracker.domain.repository.LocalRepository
import az.cryptotracker.domain.repository.NetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {

    @Singleton
    @Binds
    abstract fun bindLocalRepository(repositoryImpl: LocalRepositoryImpl): LocalRepository

    @Singleton
    @Binds
    abstract fun bindNetworkRepository(repositoryImpl: NetworkRepositoryImpl): NetworkRepository
}