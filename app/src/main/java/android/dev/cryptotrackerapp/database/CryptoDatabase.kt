package android.dev.cryptotrackerapp.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [CoinEntity::class], version = 1)
abstract class CryptoDatabase: RoomDatabase() {
    abstract fun coinDao(): CoinDao
}