package amunoz.es.innocv.presenter

import amunoz.es.innocv.NewUser
import amunoz.es.innocv.User
import amunoz.es.innocv.interactor.CreateEditInteractor
import amunoz.es.innocv.view.CreateEditActivity
import amunoz.es.innocv.view.CreateEditView


class CreateEditPresenterImpl(val interactor: CreateEditInteractor, private var view: CreateEditView? ) : CreateEditPresenter, CreateEditListener {
    override fun fabClicked(user: User, action: String, createEditView: CreateEditView) {
        view = createEditView
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

    override fun createeUser(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(descError: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setView(createEditView: CreateEditView) {

    }

    override fun updateUser(user: User) {}





}