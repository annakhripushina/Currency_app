package com.example.currency_app.data

import com.example.currency_app.data.model.CurrencyListModel
import retrofit2.http.GET

interface CurrencyService {
    @GET("daily_json.js")
    suspend fun getCurrencyList(): CurrencyListModel
}
