package amunoz.es.innocv.view

import amunoz.es.innocv.model.User


interface CreateEditView {
    fun navigateToMainActivity()
    fun showProgress()
    fun hideProgress()
    fun showAlertDialog(error: String)
    fun showUserDetails(user: User)
}