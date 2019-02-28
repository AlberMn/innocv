package amunoz.es.innocv.interactor

import amunoz.es.innocv.model.NewUser
import amunoz.es.innocv.model.User
import amunoz.es.innocv.presenter.CreateEditListener

interface CreateEditInteractor {

    fun updateUser(user: User, listener : CreateEditListener?)
    fun insertUser(newUser: NewUser, listener: CreateEditListener?)
    fun getUserById(id: Int, listener: CreateEditListener?)
}
