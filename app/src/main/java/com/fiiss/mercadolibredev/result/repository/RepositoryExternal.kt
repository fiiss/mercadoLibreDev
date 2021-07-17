package com.fiiss.mercadolibredev.result.repository

import com.fiiss.mercadolibredev.app.ServiceRestApp
import com.fiiss.mercadolibredev.result.controller.ResultController
import com.fiiss.mercadolibredev.result.interfaces.ResultInterface
import com.fiiss.mercadolibredev.result.model.ResponseListProduct
import com.google.firebase.crashlytics.FirebaseCrashlytics
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryExternal(controller: ResultController) : ResultInterface.RepositoryExternal {

    private var controlller: ResultInterface.Controller = controller

    override fun listFoundProducts(query: String, serviceRestApp: ServiceRestApp?) {

        serviceRestApp!!.serviceListProduct(query).enqueue(object : Callback<ResponseListProduct?> {
            override fun onResponse(call: Call<ResponseListProduct?>, response: Response<ResponseListProduct?>) {
                if (response.isSuccessful) {
                    val listProduct: ResponseListProduct = response.body()!!
                    controlller.setItemsFromQuery(listProduct)
                } else {
                    controlller.setErrorService("Error isSuccessful")
                }
            }

            override fun onFailure(call: Call<ResponseListProduct?>, t: Throwable) {
                FirebaseCrashlytics.getInstance().log("there was a failure when consulting the service: $query")
                t.message?.let {
                    controlller.setErrorService(it)
                }
            }
        })


    }
}