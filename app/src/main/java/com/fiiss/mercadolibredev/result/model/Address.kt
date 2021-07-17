package com.fiiss.mercadolibredev.result.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Address: Serializable {

    @SerializedName("state_id")
    var state_id: String? = null

    @SerializedName("state_name")
    var state_name: String? = null

    @SerializedName("city_name")
    var city_name: String? = null

}