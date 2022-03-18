package com.example.currency_app.service

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class CurrencyUpdateService {
    companion object {
        const val CURRENCY_UPD = "CURRENCY_UPD"
    }

    fun runWorkManager(context: Context) {
        val workManager = WorkManager.getInstance(context)
        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        val workerRequest =
            PeriodicWorkRequestBuilder<CurrencyUpdateWorker>(30, TimeUnit.MINUTES).setInitialDelay(
                15,
                TimeUnit.MINUTES
            )
                .setConstraints(constraints)
                .addTag(CURRENCY_UPD)
                .build()

        workManager.enqueueUniquePeriodicWork(
            CURRENCY_UPD,
            ExistingPeriodicWorkPolicy.KEEP,
            workerRequest
        )
    }
}