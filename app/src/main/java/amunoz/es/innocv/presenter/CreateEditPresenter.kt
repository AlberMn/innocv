package amunoz.es.innocv.presenter

import amunoz.es.innocv.model.User
import amunoz.es.innocv.view.CreateEditView


interface CreateEditPresenter {

    fun fabClicked(user: User, action: String)
    fun viewLoaded(createEditView: CreateEditView)
    fun getUserById(id: Int)
}