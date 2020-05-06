package com.myproject.bananaz.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {

    @FormUrlEncoded
    @POST("login")
    fun userLogin(@Field("email")email:String,
                  @Field("password") password:String) : Call<ResponseBody>


}