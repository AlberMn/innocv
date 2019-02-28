package amunoz.es.innocv.interactor

import amunoz.es.innocv.INNOCVApi
import amunoz.es.innocv.model.NewUser
import amunoz.es.innocv.model.User
import amunoz.es.innocv.presenter.CreateEditListener
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class CreateEditInteractorImpl (val innocvApi: INNOCVApi, private var listener: CreateEditListener?) : CreateEditInteractor  {
    override fun updateUser(user: User, listener: CreateEditListener?) {
        this.listener =listener
        val call = innocvApi.updateUser(user)
        call.enqueue(object : retrofit2.Callback<Void> {
            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                listener?.onError(t?.message!!)
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
                listener?.onError(t?.message!!)
            }

            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                listener?.onSuccess()
            }
        })
    }

    override fun getUserById(id: Int, listener: CreateEditListener?) {
        this.listener =listener
        val call = innocvApi.getUserById(id)
        call.enqueue(object : retrofit2.Callback<User> {
            override fun onFailure(call: Call<User>?, t: Throwable?) {
                listener?.onError(t?.message!!)
            }

            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                if (response?.code()==200) onSuccess(response?.body())
            }
        })
    }

    private fun onSuccess(user: User?) {
        if (user?.name == null) user?.name = "null"

        try {

            val newFormattedDate = formatDate(user?.birthdate!!)
            user.birthdate = newFormattedDate

        }catch (e : Exception) {
            user?.birthdate = "01 - 01 - 1970"
        }
        listener?.onGetUserByIdSuccess(user!!)

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
