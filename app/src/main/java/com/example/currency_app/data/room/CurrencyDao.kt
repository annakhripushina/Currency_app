package com.example.currency_app.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currency_app.data.entity.Currency

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrency(currency: Currency?)

    @Query("DELETE FROM currencyTable")
    suspend fun deleteAll()

    @Query("SELECT * FROM currencyTable")
    suspend fun getAll(): List<Currency>

}