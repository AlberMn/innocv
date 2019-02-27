package amunoz.es.innocv.presenter

import amunoz.es.innocv.view.MainView

interface MainPresenter {
    fun setView(mainView: MainView)
    fun deleteUser(id: Int)
}