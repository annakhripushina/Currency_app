package com.example.currency_app.presentation.view.currencyConverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.currency_app.R
import com.example.currency_app.dagger.CurrencyApp
import com.example.currency_app.dagger.module.viewModel.CurrencyViewModelFactory
import com.example.currency_app.data.entity.Currency
import javax.inject.Inject

class CurrencyConverterActivity : Fragment() {
    @Inject
    lateinit var viewModelFactory: CurrencyViewModelFactory
    lateinit var viewModel: CurrencyConverterViewModel
    private lateinit var currencyItem: Currency
    private lateinit var amountRubles: EditText
    private lateinit var amountValute: EditText
    private lateinit var valuteDescription: TextView
    private lateinit var valuteCode: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        CurrencyApp.appComponentViewModel.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CurrencyConverterViewModel::class.java)

        return inflater.inflate(
            R.layout.currency_converter,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        currencyItem = viewModel.currencyItem
        setValuteDescription(currencyItem)
        setClickListeners()

    }

    private fun setClickListeners() {
        amountRubles.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (amountRubles.text.isNotEmpty()) {
                    amountValute.setText(viewModel.onGetAmountValute(amountRubles))
                } else {
                    amountValute.setText("")
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }
        })
    }

    private fun setValuteDescription(currencyItem: Currency) {
        val currencyNominal = currencyItem.nominal.toString()
        val currencyValue = currencyItem.value.toString()
        val valuteDescriptionText =
            "*" + currencyNominal + " " + currencyItem.name + " = " + currencyValue + " руб."
        valuteDescription.text = valuteDescriptionText
        valuteCode.text = currencyItem.charCode
    }

    private fun initViews(view: View) {
        amountRubles = view.findViewById(R.id.editTextAmountRubles)
        amountValute = view.findViewById(R.id.editTextAmountValute)
        valuteDescription = view.findViewById(R.id.textViewValuteDescription)
        valuteCode = view.findViewById(R.id.textViewValuteCode)
    }

}