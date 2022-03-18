package com.example.currency_app.dagger.component

import com.example.currency_app.dagger.module.*
import com.example.currency_app.data.CurrencyService
import com.example.currency_app.data.ICurrencyRepository
import com.example.currency_app.data.room.CurrencyDao
import com.example.currency_app.domain.ICurrencyInteractor
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RoomDbModule::class, RetrofitModule::class, RepositoryModule::class, InteractorModule::class, ServiceModule::class])
interface AppComponent {
    fun getCurrencyDAO(): CurrencyDao
    fun provideCurrencyService(): CurrencyService
    fun provideCurrencyInteractor(): ICurrencyInteractor
    fun provideCurrencyRepository(): ICurrencyRepository
}