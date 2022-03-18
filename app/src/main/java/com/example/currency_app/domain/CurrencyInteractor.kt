package com.example.currency_app.domain

import com.example.currency_app.data.ICurrencyRepository
import com.example.currency_app.data.entity.Currency
import com.example.currency_app.data.model.toDomainModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class CurrencyInteractor @Inject constructor(private val currencyRepository: ICurrencyRepository) :
    ICurrencyInteractor {
    private var items = ArrayList<Currency>()

    override var prefsFirstRun: Boolean = false

    override lateinit var currencyItem: Currency

    override fun onSetCurrencyItem(currency: Currency) {
        currencyItem = currency
    }

    override fun onSetPrefsFirstRun(prefsValue: Boolean) {
        prefsFirstRun = prefsValue
    }

    override suspend fun getCurrency(): List<Currency> {
        items.clear()
        return currencyRepository.getCurrencyList().getValute().values.map { it.toDomainModel() }
    }

}