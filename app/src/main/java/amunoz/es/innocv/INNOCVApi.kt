package amunoz.es.innocv

import retrofit2.Call
import retrofit2.http.*

interface INNOCVApi {

    @GET("/api/User")
    fun getUsers( ): Call<ArrayList<User>>

    @GET("/api/User/{id}")
    fun getUserById(@Path("id") id: Int): Call<User>

    @DELETE("/api/User/{id}")
    fun deleteUser(@Path("id") id: Int) : Call<Void>


    @POST("/api/User")
    fun insertUser(@Body user: NewUser): Call<Void>

    @PUT("/api/User")
    fun updateUser(@Body user: User): Call <Void>
}