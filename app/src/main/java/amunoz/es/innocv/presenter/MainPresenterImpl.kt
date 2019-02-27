package amunoz.es.innocv.presenter

import amunoz.es.innocv.User
import amunoz.es.innocv.interactor.MainInteractor
import amunoz.es.innocv.view.MainView

class MainPresenterImpl(val interactor: MainInteractor, private var view: MainView?) : MainPresenter, MainPresenterListener {
    override fun onSuccess(list: ArrayList<User>) {
        view?.showUsers(list)
    }

    override fun setView(mainView: MainView) {
        view = mainView
        getListOfUsers()
    }

    override fun deleteUser(id: Int) {
        interactor.deleteUser(id)
    }

     private fun getListOfUsers()= interactor.let {

         it.getAllUsers(this)


    }

}