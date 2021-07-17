package com.fiiss.mercadolibredev.result.interfaces

import com.fiiss.mercadolibredev.app.ServiceRestApp
import com.fiiss.mercadolibredev.result.model.ResponseListProduct


interface ResultInterface {

    interface View {
        fun responseProductList(responseListProduct: ResponseListProduct)
        fun responseError(error: String)
    }

    interface RepositoryExternal {
        fun listFoundProducts(query: String, serviceRestApp: ServiceRestApp?)
    }

    interface RepositoryLocal

    interface Controller {
        // GET action
        fun getItemsFromQuery(query: String, serviceRestApp: ServiceRestApp?)

        // SET action
        fun setItemsFromQuery(responseListProduct: ResponseListProduct)
        fun setErrorService(error: String)

        fun contrllergoToNextScreen(view: android.view.View?)
    }

}