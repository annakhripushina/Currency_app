package com.example.currency_app

import com.example.currency_app.data.entity.Currency
import com.example.currency_app.domain.ICurrencyInteractor
import com.example.currency_app.presentation.view.currencyConverter.CurrencyConverterViewModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class CurrencyConverterMockitoTest {

    @Test
    fun `WHEN computation amount valute EXPECT get 1,0000`() {
        val currencyInteractor: ICurrencyInteractor = mock()

        val currencyConverterViewModel = CurrencyConverterViewModel(currencyInteractor)
        whenever(currencyInteractor.currencyItem).thenReturn(
            Currency(
                "R01235",
                "840",
                "USD",
                1,
                "Доллар США",
                1.0,
                1.0
            )
        )

        val actual = currencyConverterViewModel.onComputationAmountValute(1.0)

        val expected = "1.0000"
        assertEquals(expected, actual)
    }

}