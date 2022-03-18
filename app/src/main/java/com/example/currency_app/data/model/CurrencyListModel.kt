package com.example.currency_app.data.model

import com.google.gson.annotations.SerializedName

data class CurrencyListModel(
    @SerializedName("Date") val date: String,
    @SerializedName("PreviousDate") val previousDate: String,
    @SerializedName("PreviousURL") val previousURL: String,
    @SerializedName("Timestamp") val timestamp: String,
    @SerializedName("Valute") var valute: HashMap<String, CurrencyModel>
) {
    fun getValute(): Map<String, CurrencyModel> {
        return valute
    }
}