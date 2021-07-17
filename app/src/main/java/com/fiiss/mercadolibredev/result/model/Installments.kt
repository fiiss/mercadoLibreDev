package com.fiiss.mercadolibredev.result.model

import com.google.gson.annotations.SerializedName

class Installments {

    @SerializedName("quantity")
    var quantity: String? = "0"

    @SerializedName("amount")
    var amount: Double? = 0.0

    @SerializedName("rate")
    var rate: Double? = 0.0

    @SerializedName("currency_id")
    var currency_id: String? = ""

}