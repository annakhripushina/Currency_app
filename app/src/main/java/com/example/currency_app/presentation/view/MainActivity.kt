package com.example.currency_app.presentation.view

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.currency_app.R
import com.example.currency_app.dagger.CurrencyApp
import com.example.currency_app.dagger.module.viewModel.CurrencyViewModelFactory
import com.example.currency_app.presentation.view.currencyList.CurrencyListActivity
import com.example.currency_app.presentation.view.currencyList.CurrencyListViewModel
import com.example.currency_app.service.CurrencyUpdateService
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    companion object {
        const val FIRST_RUN = "firstrun"
    }

    @Inject
    lateinit var viewModelFactory: CurrencyViewModelFactory
    lateinit var viewModel: CurrencyListViewModel

    private val currencyUpdateService = CurrencyUpdateService()
    var prefsFirstRun: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefsFirstRun = getSharedPreferences("com.example.currency_app", MODE_PRIVATE)

        currencyUpdateService.runWorkManager(applicationContext)

        CurrencyApp.appComponentViewModel.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CurrencyListViewModel::class.java)

        if (prefsFirstRun?.getBoolean(FIRST_RUN, true) == true) {
            viewModel.onSetPrefs(true)
        }

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerActivity, CurrencyListActivity())
                .commit()
        }

        onNewIntent(intent)

    }

    override fun onResume() {
        super.onResume()
        if (prefsFirstRun?.getBoolean(FIRST_RUN, true) == true) {
            prefsFirstRun?.edit()?.putBoolean(FIRST_RUN, false)?.apply()
            viewModel.onSetPrefs(false)
        }
    }
}