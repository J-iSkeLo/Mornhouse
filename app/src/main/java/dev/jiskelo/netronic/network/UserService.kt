package dev.jiskelo.netronic.network

import dev.jiskelo.netronic.structs.Response
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {

    @GET("api")
    suspend fun getUsers(@Query("results") results:Int) :Response
}