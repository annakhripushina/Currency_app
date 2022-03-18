package com.example.currency_app.dagger.module

import com.example.currency_app.data.CurrencyRepository
import com.example.currency_app.data.CurrencyService
import com.example.currency_app.data.ICurrencyRepository
import com.example.currency_app.data.room.CurrencyDao
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class RepositoryModule {
    @Reusable
    @Provides
    fun provideCurrencyRepository(
        currencyDao: CurrencyDao,
        currencyService: CurrencyService
    ): ICurrencyRepository =
        CurrencyRepository(currencyDao, currencyService)

}