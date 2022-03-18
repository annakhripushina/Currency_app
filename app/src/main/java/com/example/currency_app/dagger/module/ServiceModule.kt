package com.example.currency_app.dagger.module

import com.example.currency_app.data.CurrencyService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit

@Module
class ServiceModule {
    @Reusable
    @Provides
    fun provideCurrencyService(retrofit: Retrofit): CurrencyService =
        retrofit.create(CurrencyService::class.java)

}