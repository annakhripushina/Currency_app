package com.example.currency_app

import com.example.currency_app.domain.ICurrencyInteractor
import com.example.currency_app.presentation.view.currencyConverter.CurrencyConverterViewModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.kotlin.mock

@RunWith(Parameterized::class)
class CurrencyConverterParameterizedTest(
    private val nominal: Int,
    private val value: Double,
    private val amountRublesValue: Double,
    private val expected: Double
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(1, 1.0, 1.0, 1.0),
            arrayOf(2, 10.0, 1.0, 0.2),
            arrayOf(100, 2.5, 1.0, 40.0)
        )
    }

    @Test
    fun `WHEN computation currency value EXPECT correct result`() {
        val currencyInteractor: ICurrencyInteractor = mock()

        val currencyConverterViewModel = CurrencyConverterViewModel(currencyInteractor)

        val actual =
            currencyConverterViewModel.onComputationCurrencyValue(nominal, value, amountRublesValue)

        assertEquals(expected, actual, 0.0)
    }

}