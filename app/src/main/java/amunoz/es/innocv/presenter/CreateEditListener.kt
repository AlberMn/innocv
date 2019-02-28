package amunoz.es.innocv.presenter

import amunoz.es.innocv.model.User

interface CreateEditListener {

    fun onSuccess()
    fun onError(descError : String)
    fun onGetUserByIdSuccess(user: User)

}
