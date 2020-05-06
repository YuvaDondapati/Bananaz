package com.myproject.bananaz.model

import android.text.TextUtils
import android.util.Patterns
import java.io.Serializable

class LoginUser(val emailAddress: String, val password: String) :Serializable {

    fun isEmailValid(): Boolean
    {
        return !TextUtils.isEmpty(emailAddress) && Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()
    }


    fun isPasswordValid(): Boolean
    {
        return !TextUtils.isEmpty(password) && password.length > 5
    }

}