package amunoz.es.innocv.interactor

import amunoz.es.innocv.INNOCVApi
import amunoz.es.innocv.NewUser
import amunoz.es.innocv.User
import amunoz.es.innocv.presenter.CreateEditListener
import android.util.Log
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class CreateEditInteractorImpl (val innocvApi: INNOCVApi, private var listener: CreateEditListener?) : CreateEditInteractor  {
    override fun updateUser(user: User, listener: CreateEditListener?) {
        this.listener =listener
        val call = innocvApi.updateUser(user)
        call.enqueue(object : retrofit2.Callback<Void> {
            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                listener?.onError(t.toString())
            }

            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                listener?.onSuccess()
            }


        })
    }

    override fun insertUser(newUser: NewUser, listener: CreateEditListener?) {
        this.listener =listener
        val call = innocvApi.insertUser(newUser)
        call.enqueue(object : retrofit2.Callback<Void> {
            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                listener?.onError(t.toString())
            }

            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                listener?.onSuccess()
            }



        })
    }

}
