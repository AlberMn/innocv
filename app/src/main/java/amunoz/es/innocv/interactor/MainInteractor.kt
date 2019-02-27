package amunoz.es.innocv.interactor

import amunoz.es.innocv.presenter.MainPresenterListener

interface MainInteractor {
    fun getAllUsers(listener : MainPresenterListener?)
    fun deleteUser(id: Int )
}