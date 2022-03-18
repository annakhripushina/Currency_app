package com.example.currency_app.dagger.component

import com.example.currency_app.service.CurrencyUpdateWorker
import dagger.Component
import javax.inject.Scope

@ServiceScope
@Component(dependencies = [AppComponent::class])
interface WorkerComponent {
    fun inject(currencyUpdateWorker: CurrencyUpdateWorker)
}

@Scope
annotation class ServiceScope