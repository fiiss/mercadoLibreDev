package com.fiiss.mercadolibredev.result.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResponseListProduct: Serializable {

    @SerializedName("site_id")
    var site_id: String? = null

    @SerializedName("results")
    var listResult: List<Results>? = ArrayList()

}