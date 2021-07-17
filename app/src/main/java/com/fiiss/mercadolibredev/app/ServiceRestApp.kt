package com.fiiss.mercadolibredev.app

import com.fiiss.mercadolibredev.result.model.ResponseListProduct
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ServiceRestApp {

    @Headers("Content-Type: application/json")
    @GET("sites/MLA/search")
    fun serviceListProduct(@Query("q") query: String?): Call<ResponseListProduct?>


}

