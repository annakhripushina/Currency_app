package com.example.currency_app.presentation.view.currencyList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.currency_app.data.ICurrencyRepository
import com.example.currency_app.data.entity.Currency
import com.example.currency_app.domain.ICurrencyInteractor
import com.example.currency_app.utils.SingleLiveEvent
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class CurrencyListViewModel
@Inject constructor(
    private val currencyInteractor: ICurrencyInteractor,
    private val currencyRepository: ICurrencyRepository
) : AndroidViewModel(Application()) {

    private val mCurrencyList: MutableLiveData<List<Currency>> = SingleLiveEvent()
    private val mError: MutableLiveData<String> = SingleLiveEvent()
    var prefsFirstRun: Boolean = currencyInteractor.prefsFirstRun

    fun onSetPrefs(prefsValue: Boolean) {
        currencyInteractor.onSetPrefsFirstRun(prefsValue)
    }

    val currencyList
        get() = mCurrencyList

    val error: MutableLiveData<String>
        get() = mError

    fun onGetCurrencyListDB(): LiveData<List<Currency>> {
        viewModelScope.launch {
            try {
                mCurrencyList.value = currencyRepository.getCurrencyListDB()
            } catch (e: Exception) {
                mError.postValue("Error...")
            }
        }
        return currencyList
    }

    fun onGetCurrencyList() {
        viewModelScope.launch {
            try {
                mCurrencyList.value = currencyInteractor.getCurrency()
                currencyRepository.deleteAll()
                currencyInteractor.getCurrency().forEach {
                    currencyRepository.insertCurrency(it)
                }
            } catch (http: HttpException) {
                mError.postValue(http.code().toString())
            } catch (e: Exception) {
                mError.postValue("Network error probably...")
            }
        }
    }

    fun onSetCurrencyItem(currencyItem: Currency) {
        currencyInteractor.onSetCurrencyItem(currencyItem)
    }

}