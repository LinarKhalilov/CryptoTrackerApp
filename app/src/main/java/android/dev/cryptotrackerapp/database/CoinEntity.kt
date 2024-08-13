package android.dev.cryptotrackerapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class CoinEntity (
    @PrimaryKey val valid: String,
    val name: String,
    val symbol: String,
    val rank: Int
)