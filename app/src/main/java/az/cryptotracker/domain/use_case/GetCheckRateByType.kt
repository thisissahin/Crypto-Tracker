package az.cryptotracker.domain.use_case

import az.cryptotracker.domain.model.enums.enums.CryptoType
import az.cryptotracker.domain.repository.LocalRepository
import javax.inject.Inject

class GetCheckRateByType @Inject constructor(
    private val ratesRepository: LocalRepository
) {
    suspend operator fun invoke(type: CryptoType) = ratesRepository.getCheckRateByType(type)
}