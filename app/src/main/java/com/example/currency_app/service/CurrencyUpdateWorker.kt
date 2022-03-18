package com.example.currency_app.service

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.currency_app.dagger.CurrencyApp
import com.example.currency_app.dagger.component.DaggerWorkerComponent
import com.example.currency_app.data.ICurrencyRepository
import com.example.currency_app.domain.CurrencyInteractor
import retrofit2.HttpException
import javax.inject.Inject

class CurrencyUpdateWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {
    @Inject
    lateinit var currencyInteractor: CurrencyInteractor

    @Inject
    lateinit var currencyRepository: ICurrencyRepository

    override suspend fun doWork(): Result {
        DaggerWorkerComponent.builder()
            .appComponent((applicationContext as? CurrencyApp)?.getAppComponent())
            .build()
            .inject(this)

        return try {
            currencyRepository.deleteAll()
            currencyInteractor.getCurrency().forEach {
                currencyRepository.insertCurrency(it)
            }
            Result.success()
        } catch (http: HttpException) {
            Result.failure()
        } catch (e: Exception) {
            Result.retry()
        }
    }

}