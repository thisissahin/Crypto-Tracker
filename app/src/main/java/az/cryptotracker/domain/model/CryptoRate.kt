package az.cryptotracker.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "crypto_table",
    indices = [Index(value = ["Name","Value"], unique = true)]
)
data class CryptoRate(
    @ColumnInfo(name = "Name")
    var cryptoName: String,
    @ColumnInfo(name = "Value")
    var value: Double
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}