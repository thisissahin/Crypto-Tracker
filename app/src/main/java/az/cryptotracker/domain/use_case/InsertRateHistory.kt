package az.cryptotracker.domain.use_case

import az.cryptotracker.domain.model.CryptoRate
import az.cryptotracker.domain.repository.LocalRepository
import javax.inject.Inject

class InsertRateHistory @Inject constructor(
    private val localRepository: LocalRepository
) {
    suspend operator fun invoke(rate: CryptoRate) = localRepository.insertRateHistory(rate)
}