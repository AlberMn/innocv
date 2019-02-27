package amunoz.es.innocv.interactor

import amunoz.es.innocv.INNOCVApi
import amunoz.es.innocv.User
import amunoz.es.innocv.presenter.MainPresenterListener
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainInteractorImpl(val innocvApi: INNOCVApi, private var listener: MainPresenterListener?) : MainInteractor {

    override fun getAllUsers(listener: MainPresenterListener?) {
        this.listener = listener
        val call = innocvApi.getUsers()
        var userList : ArrayList<User> = ArrayList()
        call.enqueue(object : Callback<ArrayList<User>> {
            override fun onFailure(call: Call<ArrayList<User>>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<ArrayList<User>>?, response: Response<ArrayList<User>>?) {
                onGetMoviewsSuccess(response?.body())
                Log.d("onresponse","shou " + userList.toString())
            }


        })
        Log.d("interactor","shou " + userList.toString())

    }

    override fun deleteUser(id: Int) {
       val call = innocvApi.deleteUser(id)
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                Log.d("ss", "failure")
            }

            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                Log.d("ss", "ok")
            }

        })
    }
    private fun onGetMoviewsSuccess(usersList: ArrayList<User>?)  {
        Log.d(" ", "dsdsd")

        if (usersList != null) {
            for(user : User in usersList) {
                if (user.name == null) user.name = "null"
            }
        }
        listener?.onSuccess(usersList!!)
    }



}