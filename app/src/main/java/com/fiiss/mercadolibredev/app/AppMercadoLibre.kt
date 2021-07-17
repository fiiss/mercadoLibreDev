package com.fiiss.mercadolibredev.app

import android.app.Application
import android.content.Context

class AppMercadoLibre: Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: AppMercadoLibre? = null
        lateinit  var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

}