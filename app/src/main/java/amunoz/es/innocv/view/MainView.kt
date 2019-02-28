package amunoz.es.innocv.view

import amunoz.es.innocv.model.User

interface MainView {
    fun showUsers(users: ArrayList<User>?)
    fun showProgress()
    fun hideProgress()
    fun showAlertDialog(descError: String)
}