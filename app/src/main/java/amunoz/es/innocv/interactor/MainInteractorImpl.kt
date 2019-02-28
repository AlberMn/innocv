package amunoz.es.innocv.interactor

import amunoz.es.innocv.INNOCVApi
import amunoz.es.innocv.model.User
import amunoz.es.innocv.presenter.MainPresenterListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class MainInteractorImpl(val innocvApi: INNOCVApi, private var listener: MainPresenterListener?) : MainInteractor {

    override fun getAllUsers(listener: MainPresenterListener?) {
        this.listener = listener
        val call = innocvApi.getUsers()
        var userList : ArrayList<User> = ArrayList()
        call.enqueue(object : Callback<ArrayList<User>> {
            override fun onFailure(call: Call<ArrayList<User>>?, t: Throwable?) {
                listener?.onError(t?.message!!)
            }

            override fun onResponse(call: Call<ArrayList<User>>?, response: Response<ArrayList<User>>?) {
                if (response?.code()==200) onGetUsersSuccess(response.body())

            }

        })
    }

    override fun deleteUser(id: Int) {
       val call = innocvApi.deleteUser(id)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {

            }

            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                listener?.onError(t?.message!!)
            }


        })
    }

    private fun onGetUsersSuccess(usersList: ArrayList<User>?)  {
        if (usersList != null) {
            for(user : User in usersList) {
                if (user.name == null) user.name = "null"

                try {

                    val newFormattedDate = formatDate(user.birthdate)
                    user.birthdate = newFormattedDate

                }catch (e : Exception) {
                    user.birthdate = "01 - 01 - 1970"
                }
            }
        }
        listener?.onSuccess(usersList!!)
    }

    private fun formatDate(serverDate: String) :String {
        val serverDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS",
                Locale.getDefault())
        serverDateFormat.timeZone = TimeZone.getTimeZone("GMT")
        var parsedDate = serverDateFormat.parse(serverDate)

        val clientDateFormat = SimpleDateFormat("dd - MM - yyyy",
                Locale.getDefault())
        clientDateFormat.timeZone = TimeZone.getTimeZone("GMT")
        return clientDateFormat.format(parsedDate)
    }



}