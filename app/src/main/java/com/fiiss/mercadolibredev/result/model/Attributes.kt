package com.fiiss.mercadolibredev.result.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Attributes: Serializable {

    @SerializedName("source")
    var source: String? = null

    @SerializedName("id")
    var id: String? = null

    @SerializedName("value_name")
    var value_name: String? = null

    @SerializedName("attribute_group_id")
    var attribute_group_id: String? = null

    @SerializedName("attribute_group_name")
    var attribute_group_name: String? = null

    @SerializedName("name")
    var name: String? = null

}