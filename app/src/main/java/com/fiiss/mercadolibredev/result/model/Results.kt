package com.fiiss.mercadolibredev.result.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Results: Serializable {

    @SerializedName("id")
    var id: String? = null

    @SerializedName("site_id")
    var site_id: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("price")
    var price: Double? = null

    @SerializedName("currency_id")
    var currency_id: String? = null

    @SerializedName("available_quantity")
    var available_quantity: Int? = 0

    @SerializedName("sold_quantity")
    var sold_quantity: Int? = 0

    @SerializedName("permalink")
    var permalink: String? = null

    @SerializedName("thumbnail")
    var thumbnail: String? = null

    @SerializedName("condition")
    var condition: String? = null

    @SerializedName("installments")
    var installments: Installments? = null

    @SerializedName("address")
    var address: Address? = null

    @SerializedName("shipping")
    var shipping: Shipping? = null

    @SerializedName("attributes")
    var attributes: List<Attributes>? = null

}