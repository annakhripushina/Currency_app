package com.example.currency_app.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.currency_app.data.entity.Currency

@Database(
    entities = [Currency::class],
    version = 1
)
abstract class CurrencyRoomDB : RoomDatabase() {
    abstract fun getCurrencyDao(): CurrencyDao

    companion object {
        @Volatile
        private var INSTANCE: CurrencyRoomDB? = null

        fun getDatabase(
            context: Context
        ): CurrencyRoomDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CurrencyRoomDB::class.java,
                    "currency_database.db"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }

}