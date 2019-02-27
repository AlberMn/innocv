package amunoz.es.innocv.presenter

import amunoz.es.innocv.User

interface MainPresenterListener {

    fun onSuccess(list: ArrayList<User>)
}