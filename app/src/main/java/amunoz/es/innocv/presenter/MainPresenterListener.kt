package amunoz.es.innocv.presenter

import amunoz.es.innocv.model.User

interface MainPresenterListener {

    fun onSuccess(list: ArrayList<User>)
    fun onError(descError: String)
}