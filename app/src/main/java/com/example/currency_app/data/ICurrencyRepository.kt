package com.example.currency_app.data

import com.example.currency_app.data.entity.Currency
import com.example.currency_app.data.model.CurrencyListModel

interface ICurrencyRepository {
    suspend fun getCurrencyListDB(): List<Currency>

    suspend fun insertCurrency(currencyItem: Currency)

    suspend fun deleteAll()

    suspend fun getCurrencyList(): CurrencyListModel
}
