package com.fiiss.mercadolibredev.result.repository

import com.fiiss.mercadolibredev.result.controller.ResultController
import com.fiiss.mercadolibredev.result.interfaces.ResultInterface

class RepositoryLocal(controller: ResultController) : ResultInterface.RepositoryLocal {

    private var controlller: ResultInterface.Controller = controller

}