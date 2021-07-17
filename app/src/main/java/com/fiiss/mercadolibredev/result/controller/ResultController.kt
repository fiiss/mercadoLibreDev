package com.fiiss.mercadolibredev.result.controller

import android.view.View
import androidx.navigation.findNavController
import com.fiiss.mercadolibredev.R
import com.fiiss.mercadolibredev.app.ServiceRestApp
import com.fiiss.mercadolibredev.result.interfaces.ResultInterface
import com.fiiss.mercadolibredev.result.model.ResponseListProduct
import com.fiiss.mercadolibredev.result.repository.RepositoryExternal
import com.fiiss.mercadolibredev.result.repository.RepositoryLocal
import com.fiiss.mercadolibredev.result.view.SearchResult

class ResultController(searchResult: SearchResult) : ResultInterface.Controller {

    private var view: ResultInterface.View? = null
    private var repositoryExternal: ResultInterface.RepositoryExternal? = null
    private var repositoryLocal: ResultInterface.RepositoryLocal? = null

    init {
        this.view = searchResult
        repositoryExternal = RepositoryExternal(this)
        repositoryLocal = RepositoryLocal(this)
    }

    override fun getItemsFromQuery(query: String, serviceRestApp: ServiceRestApp?) {
        repositoryExternal?.listFoundProducts(query, serviceRestApp)
    }

    override fun setErrorService(error: String) {
        view?.responseError(error)
    }

    override fun contrllergoToNextScreen(view: View?) {
        view!!.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

    override fun setItemsFromQuery(responseListProduct: ResponseListProduct) {
        view?.responseProductList(responseListProduct)
    }




}