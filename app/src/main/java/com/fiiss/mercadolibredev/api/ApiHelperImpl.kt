package com.fiiss.mercadolibredev.api

import com.fiiss.mercadolibredev.app.ServiceRestApp
import com.fiiss.mercadolibredev.result.model.ResponseListProduct
import retrofit2.Call

class ApiHelperImpl(private val apiInterface: ServiceRestApp) : ApiHelper {


    override fun getserviceListProduct(query: String): Call<ResponseListProduct?> = apiInterface.serviceListProduct(query)

}