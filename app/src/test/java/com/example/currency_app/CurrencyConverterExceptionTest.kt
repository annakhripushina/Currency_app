package com.example.currency_app

import com.example.currency_app.domain.ICurrencyInteractor
import com.example.currency_app.presentation.view.currencyConverter.CurrencyConverterViewModel
import org.junit.Test
import org.mockito.kotlin.mock

class CurrencyConverterExceptionTest {
    @Test(expected = IllegalArgumentException::class)
    fun `WHEN value by zero EXPECT illegal argument exception`() {
        val currencyInteractor: ICurrencyInteractor = mock()

        val currencyConverterViewModel = CurrencyConverterViewModel(currencyInteractor)

        currencyConverterViewModel.onComputationCurrencyValue(1, 0.0, 1.0)
    }
}