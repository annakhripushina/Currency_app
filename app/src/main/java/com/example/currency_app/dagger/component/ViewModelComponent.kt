package com.example.currency_app.dagger.component

import com.example.currency_app.dagger.module.viewModel.CurrencyViewModelModule
import com.example.currency_app.presentation.view.MainActivity
import com.example.currency_app.presentation.view.currencyConverter.CurrencyConverterActivity
import com.example.currency_app.presentation.view.currencyList.CurrencyListActivity
import dagger.Component
import javax.inject.Scope

@ViewModelScope
@Component(dependencies = [AppComponent::class], modules = [CurrencyViewModelModule::class])
interface ViewModelComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(currencyListActivity: CurrencyListActivity)
    fun inject(currencyConverterActivity: CurrencyConverterActivity)
}

@Scope
annotation class ViewModelScope