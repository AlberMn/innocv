package amunoz.es.innocv.presenter

import amunoz.es.innocv.model.User
import amunoz.es.innocv.interactor.MainInteractor
import amunoz.es.innocv.view.MainView

class MainPresenterImpl(val interactor: MainInteractor, private var view: MainView?) : MainPresenter, MainPresenterListener {


    override fun setView(mainView: MainView) {
        view = mainView
        view?.showProgress()
        getListOfUsers()
    }

    override fun deleteUser(id: Int) {
        interactor.deleteUser(id)
    }

     private fun getListOfUsers()= interactor.let {

         it.getAllUsers(this)
     }

    override fun onSuccess(list: ArrayList<User>) {
        view?.hideProgress()
        view?.showUsers(list)
    }

    override fun onError(descError: String) {
        view?.hideProgress()
        view?.showAlertDialog(descError)
    }


}