package com.example.currency_app

import com.example.currency_app.data.entity.Currency
import com.example.currency_app.domain.ICurrencyInteractor
import com.example.currency_app.presentation.view.currencyConverter.CurrencyConverterViewModel
import org.junit.Assert
import org.junit.Test

class CurrencyConverterStubTest {
    @Test
    fun `WHEN computation currency value EXPECT get 1,0000`() {
        val currencyInteractor = object : ICurrencyInteractor {
            override var currencyItem: Currency
                get() = Currency("R01235", "840", "USD", 1, "Доллар США", 1.0, 1.0)
                set(value) {}
            override var prefsFirstRun: Boolean
                get() = false
                set(value) {}

            override fun onSetCurrencyItem(currency: Currency) {
            }

            override fun onSetPrefsFirstRun(prefsValue: Boolean) {
            }

            override suspend fun getCurrency(): List<Currency> {
                return listOf()
            }

        }

        val currencyConverterViewModel = CurrencyConverterViewModel(currencyInteractor)

        val actual = currencyConverterViewModel.onComputationCurrencyValue(
            currencyConverterViewModel.currencyItem.nominal,
            currencyConverterViewModel.currencyItem.value,
            1.0
        )

        val expected = 1.0

        Assert.assertEquals(expected, actual, 0.0)
    }
}