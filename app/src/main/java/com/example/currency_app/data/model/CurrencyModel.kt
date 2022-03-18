package com.example.currency_app.data.model

import com.example.currency_app.data.entity.Currency
import com.google.gson.annotations.SerializedName

data class CurrencyModel(
    @SerializedName("ID") val id: String,
    @SerializedName("NumCode") val numCode: String,
    @SerializedName("CharCode") val charCode: String,
    @SerializedName("Nominal") val nominal: Int,
    @SerializedName("Name") val name: String,
    @SerializedName("Value") val value: Double,
    @SerializedName("Previous") val previous: Double
)

internal fun CurrencyModel.toDomainModel() = Currency(
    id = this.id,
    numCode = this.numCode,
    charCode = this.charCode,
    nominal = this.nominal,
    name = this.name,
    value = this.value,
    previous = this.previous
)

