package az.cryptotracker.domain.model

data class CryptoRange(
    var cryptoName: String, var minValue: Double, var maxValue: Double
)