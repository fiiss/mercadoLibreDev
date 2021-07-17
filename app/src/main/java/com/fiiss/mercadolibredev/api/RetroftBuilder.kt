package com.fiiss.mercadolibredev.api

import com.fiiss.mercadolibredev.BuildConfig
import com.fiiss.mercadolibredev.app.ServiceRestApp
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private fun getRetrofit(): Retrofit {
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiInterface: ServiceRestApp = getRetrofit().create(ServiceRestApp::class.java)

}