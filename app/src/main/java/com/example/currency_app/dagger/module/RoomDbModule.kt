package com.example.currency_app.dagger.module

import android.app.Application
import android.content.Context
import com.example.currency_app.data.room.CurrencyDao
import com.example.currency_app.data.room.CurrencyRoomDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomDbModule(private val application: Application) {
    @Singleton
    @Provides
    fun getCurrencyDAO(currencyRoomDB: CurrencyRoomDB): CurrencyDao {
        return currencyRoomDB.getCurrencyDao()
    }

    @Singleton
    @Provides
    fun getRoomDbInstance(): CurrencyRoomDB {
        return CurrencyRoomDB.getDatabase(provideAppContext())
    }

    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return application.applicationContext
    }

}