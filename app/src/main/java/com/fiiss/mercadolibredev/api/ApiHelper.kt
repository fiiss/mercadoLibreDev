package com.fiiss.mercadolibredev.api

import com.fiiss.mercadolibredev.result.model.ResponseListProduct
import retrofit2.Call

interface ApiHelper {
    fun getserviceListProduct(query: String): Call<ResponseListProduct?>
}