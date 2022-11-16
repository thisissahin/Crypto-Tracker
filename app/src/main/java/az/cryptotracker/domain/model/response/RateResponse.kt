package az.cryptotracker.domain.model.response

import az.cryptotracker.domain.model.CryptoRate
import az.cryptotracker.domain.model.enums.enums.CryptoType
import com.google.gson.annotations.SerializedName

data class RateResponse(
    @field:SerializedName("rates") val rates: CryptoDetail
) {
    fun getList(): List<CryptoValue> {
        return listOf(rates.btc, rates.eth, rates.xrp)
    }
}

data class CryptoDetail(
    @field:SerializedName("btc") val btc: CryptoValue,
    @field:SerializedName("eth") val eth: CryptoValue,
    @field:SerializedName("xrp") val xrp: CryptoValue,
)

data class CryptoValue(
    @field:SerializedName("value") val value: Double,
    @field:SerializedName("unit") val unit: CryptoType
){
    fun mapToCryptoRate():CryptoRate{
        return CryptoRate(unit.name,value)
    }
}