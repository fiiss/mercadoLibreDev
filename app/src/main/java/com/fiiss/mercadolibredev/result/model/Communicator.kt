package com.fiiss.mercadolibredev.result.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Communicator : ViewModel() {

    val selected = MutableLiveData<Results>()

    val listProduct = MutableLiveData<ResponseListProduct>()

    fun select(item: Results) {
        selected.value = item
    }

    fun setListProduct(item: ResponseListProduct) {
        listProduct.value = item
    }

    fun getListProduct() = listProduct.value
}