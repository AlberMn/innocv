package amunoz.es.innocv

import retrofit2.Call
import retrofit2.http.*

interface INNOCVApi {

    @GET("/api/User")
    fun getUsers( ): Call<ArrayList<User>>

    @GET("/api/User/{id}")
    fun getUserById(@Path("id") id: Int): Call<User>

    @DELETE("/api/User/{id}")
    fun deleteUser(@Path("id") id: Int) : Call<String>


    @POST("/api/User")
    fun insertUSer(@Body user: User): Call<String>

    @PUT("/api/User")
    fun updateUser(@Body user: User): Call <String>
}