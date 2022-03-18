package com.example.currency_app.presentation.view.currencyConverter

import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.example.currency_app.data.entity.Currency
import com.example.currency_app.domain.ICurrencyInteractor
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

class CurrencyConverterViewModel @Inject constructor(
    private val currencyInteractor: ICurrencyInteractor,
) : ViewModel() {
    private var mAmountValute: String = ""
    val currencyItem: Currency
        get() = currencyInteractor.currencyItem

    fun onGetAmountValute(amountRubles: EditText): String {
        val amountRublesValue = amountRubles.text.toString().toDouble()
        val currencyValue = (currencyItem.nominal * amountRublesValue) / currencyItem.value
        mAmountValute = BigDecimal(currencyValue).setScale(4, RoundingMode.HALF_EVEN).toString()
        return mAmountValute
    }
}