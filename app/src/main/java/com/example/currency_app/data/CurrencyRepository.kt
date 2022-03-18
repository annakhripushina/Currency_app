package com.example.currency_app.data

import com.example.currency_app.data.entity.Currency
import com.example.currency_app.data.model.CurrencyListModel
import com.example.currency_app.data.room.CurrencyDao
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val currencyDao: CurrencyDao,
    private val currencyService: CurrencyService
) : ICurrencyRepository {
    override suspend fun getCurrencyListDB(): List<Currency> {
        return currencyDao.getAll()
    }

    override suspend fun insertCurrency(currencyItem: Currency) {
        currencyDao.insertCurrency(currencyItem)
    }

    override suspend fun deleteAll() {
        currencyDao.deleteAll()
    }

    override suspend fun getCurrencyList(): CurrencyListModel {
        return currencyService.getCurrencyList()
    }

}
