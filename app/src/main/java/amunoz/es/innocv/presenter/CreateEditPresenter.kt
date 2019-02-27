package amunoz.es.innocv.presenter

import amunoz.es.innocv.User
import amunoz.es.innocv.view.CreateEditView


interface CreateEditPresenter {

    fun setView(createEditView: CreateEditView)
    fun updateUser(user : User)
    fun createeUser(user : User)
    fun fabClicked(user: User, action: String, createEditView: CreateEditView)
}