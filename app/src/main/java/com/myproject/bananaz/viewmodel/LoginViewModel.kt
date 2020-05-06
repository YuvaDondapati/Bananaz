package com.myproject.bananaz.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myproject.bananaz.di.ApiModule
import com.myproject.bananaz.di.DaggerLoginComponent
import com.myproject.bananaz.repository.UserRepository
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import yuvadon.demos.countries.util.isEmailValid
import yuvadon.demos.countries.util.isPasswordValid
import javax.inject.Inject

class LoginViewModel : ViewModel() {

    val emailAddress = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()
    val loginResponse = MutableLiveData<String>()
    var mLoginPasswordMediator = MediatorLiveData<Boolean>()

    @Inject
    lateinit var userRepository: UserRepository

    val isEmail = MediatorLiveData<Boolean>().apply {
        addSource(emailAddress) {
            value = isEmailValid(it)
        }
    }
    val isPassword = MediatorLiveData<Boolean>().apply {
        addSource(password) {
            value = isPasswordValid(it)
        }
    }

    private fun isEmailPasswordValid(): Boolean {
        if (emailAddress.value == null || password.value == null) return false
        return isEmailValid(emailAddress.value!!) && isPasswordValid(password.value!!)
    }

    init {
//        DaggerLoginComponent.builder()
//            .apiModule(ApiModule("http://34.93.40.218/banana/api/"))
//            .build()

        DaggerLoginComponent.builder()
            .baseUrl("http://34.93.40.218/banana/api/")
            .build().injectVM(this)

        mLoginPasswordMediator.addSource(emailAddress) {
            mLoginPasswordMediator.value = isEmailPasswordValid()
        }
        mLoginPasswordMediator.addSource(password) {
            mLoginPasswordMediator.value = isEmailPasswordValid()
        }
    }

    override fun onCleared() {
        // DO NOT forget to remove sources from mediator
        mLoginPasswordMediator.removeSource(emailAddress)
        mLoginPasswordMediator.removeSource(password)
    }


    fun login() {

        isLoading.value = true
        println("in login")
        userRepository.userLogin(emailAddress.toString(), password.toString())
            .enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    println("in login failure  " + t.message)
                    loginResponse.value = t.message
                    isLoading.value = false
                }

                override fun onResponse(
                    call: Call<ResponseBody>, response: Response<ResponseBody>
                ) {
                    isLoading.value = false
                    println("in login success  " + response.body().toString())
                    if (response.isSuccessful) {
                        loginResponse.value = response.body().toString()
                    } else {
                        loginResponse.value = response.errorBody().toString()
                    }
                }

            })
    }




    /*

//    fun isValidData(){
//
//    }
//    fun validateForm(editText: String) {
//        mLoginPasswordMediator.value =  isEmailValid(editText)
//    }

//
//    val validData = MediatorLiveData<Boolean>().apply {
//        addSource(emailAddress) {
//            data1 = isEmailValid(it)
//            value = data1 && data2
//        }
//        addSource(password) {
//            data2 = isPasswordValid(it)
//            value = data1 && data2
//        }
//    }
     */
/*  using RX single observable
    disposable.add(
    UserRepository().userLogin(emailAddress.toString(), password.toString())
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribeWith(object : DisposableSingleObserver<ResponseBody>(){
        override fun onSuccess(t: ResponseBody) {
            isLoading.value =false
            loginResponse.value = t.toString()
            responseError.value = false
        }

        override fun onError(e: Throwable) {
            loginResponse.value = e.message
            isLoading.value =false
            responseError.value = true
        }

    })
    */

}