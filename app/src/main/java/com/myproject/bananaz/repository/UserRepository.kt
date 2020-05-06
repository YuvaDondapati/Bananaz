package com.myproject.bananaz.repository

import com.myproject.bananaz.di.ApiModule
import com.myproject.bananaz.di.DaggerLoginComponent
import com.myproject.bananaz.network.MyApi
import okhttp3.ResponseBody
import retrofit2.Call
import javax.inject.Inject

class  UserRepository  @Inject constructor(){

    @Inject
    lateinit var api: MyApi

    fun userLogin(email: String, password: String): Call<ResponseBody> {
        return api.userLogin(email, password)
    }

    init {
        //without @component.builder
//        DaggerLoginComponent.builder()
//            .apiModule(ApiModule("http://34.93.40.218/banana/api/"))
//            .build()

        DaggerLoginComponent.builder()
            .baseUrl("http://34.93.40.218/banana/api/")
            .build()

    }
}