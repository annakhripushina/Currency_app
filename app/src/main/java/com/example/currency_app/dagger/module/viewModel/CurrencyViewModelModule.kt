package com.example.currency_app.dagger.module.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currency_app.presentation.view.currencyConverter.CurrencyConverterViewModel
import com.example.currency_app.presentation.view.currencyList.CurrencyListViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class CurrencyViewModelModule(val application: Application) {

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyListViewModel::class)
    internal abstract fun bindCurrencyListViewModel(viewModel: CurrencyListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyConverterViewModel::class)
    internal abstract fun bindCurrencyConverterViewModel(viewModel: CurrencyConverterViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: CurrencyViewModelFactory): ViewModelProvider.Factory

}