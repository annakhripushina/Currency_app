package com.example.currency_app.domain

import com.example.currency_app.data.entity.Currency

interface ICurrencyInteractor {
    var currencyItem: Currency

    var prefsFirstRun: Boolean

    fun onSetCurrencyItem(currency: Currency)

    fun onSetPrefsFirstRun(prefsValue: Boolean)

    suspend fun getCurrency(): List<Currency>
}