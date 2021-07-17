package com.fiiss.mercadolibredev.result.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Shipping: Serializable {

    @SerializedName("free_shipping")
    var free_shipping: Boolean? = null

}