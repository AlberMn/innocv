package amunoz.es.innocv.presenter

import amunoz.es.innocv.model.NewUser
import amunoz.es.innocv.model.User
import amunoz.es.innocv.interactor.CreateEditInteractor
import amunoz.es.innocv.view.CreateEditActivity
import amunoz.es.innocv.view.CreateEditView


class CreateEditPresenterImpl(val interactor: CreateEditInteractor, private var view: CreateEditView? ) : CreateEditPresenter, CreateEditListener {

    override fun viewLoaded(createEditView: CreateEditView) {
        view = createEditView
        view?.hideProgress()
    }
    override fun getUserById(id: Int) = interactor.let {
        view?.showProgress()
        it.getUserById(id, this)
    }



    override fun fabClicked(user: User, action: String) {

        view?.showProgress()
        if(action == CreateEditActivity.ACTION_EDIT) {

          updatUser(user)
        } else {
            val newUser = NewUser(user.name, user.birthdate)
            insertUser(newUser)
        }
    }
    private fun updatUser(user: User) = interactor.let {

        it.updateUser(user,this)
    }
    private fun insertUser(newUser: NewUser) = interactor.let {

        it.insertUser(newUser,this)
    }


    override fun onSuccess() {
        view?.hideProgress()
        view?.navigateToMainActivity()

    }

    override fun onError(descError: String) {
        view?.hideProgress()
        view?.showAlertDialog(descError)
    }

    override fun onGetUserByIdSuccess(user: User) {
        view?.hideProgress()
        view?.showUserDetails(user)
    }
}