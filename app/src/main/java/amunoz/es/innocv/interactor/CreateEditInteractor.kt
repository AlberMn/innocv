package amunoz.es.innocv.interactor

import amunoz.es.innocv.NewUser
import amunoz.es.innocv.User
import amunoz.es.innocv.presenter.CreateEditListener

interface CreateEditInteractor {

    fun updateUser(user: User, listener : CreateEditListener?)
    fun insertUser(newUser: NewUser, listener: CreateEditListener?)
}
