package com.example.currency_app.dagger.module

import com.example.currency_app.data.CurrencyRepository
import com.example.currency_app.domain.CurrencyInteractor
import com.example.currency_app.domain.ICurrencyInteractor
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class InteractorModule {
    @Reusable
    @Provides
    fun provideCurrencyListInteractor(currencyRepository: CurrencyRepository): ICurrencyInteractor =
        CurrencyInteractor(currencyRepository)
}