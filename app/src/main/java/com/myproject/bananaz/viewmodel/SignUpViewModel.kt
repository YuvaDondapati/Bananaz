package com.myproject.bananaz.viewmodel

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myproject.bananaz.view.LoginActivity
import com.myproject.bananaz.view.MainActivity
import yuvadon.demos.countries.util.isEmailValid
import yuvadon.demos.countries.util.isMobileNumberValid
import yuvadon.demos.countries.util.isPasswordsMatch

class SignUpViewModel : ViewModel() {

    val emailAddress = MutableLiveData<String>()
    val mobileNumber = MutableLiveData<String>()
    var address = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val cnfmPw = MutableLiveData<String>()
    val shopName = MutableLiveData<String>()

    var signUpValidation = MediatorLiveData<Boolean>()

    private fun isEmailPasswordValid(): Boolean {
        if (emailAddress.value == null || mobileNumber.value == null || address.value == null
            || password.value == null || cnfmPw.value == null || shopName.value == null
        ) return false

        return isEmailValid(emailAddress.value!!) &&
                isMobileNumberValid(mobileNumber.value!!) &&
                isPasswordsMatch(password.value!!, cnfmPw.value!!)
    }

    init {
        signUpValidation.addSource(emailAddress) {
            signUpValidation.value = isEmailPasswordValid()
        }
        signUpValidation.addSource(mobileNumber) {
            signUpValidation.value = isEmailPasswordValid()
        }
        signUpValidation.addSource(address) {
            signUpValidation.value = isEmailPasswordValid()
        }
        signUpValidation.addSource(shopName) {
            signUpValidation.value = isEmailPasswordValid()
        }
        signUpValidation.addSource(cnfmPw) {
            signUpValidation.value = isEmailPasswordValid()
        }
        signUpValidation.addSource(password) {
            signUpValidation.value = isEmailPasswordValid()
        }
    }

    fun passwordsMatch(cnfmPw: String): Boolean {
        if( password.value == null) return false
        return isPasswordsMatch(password.value!!, cnfmPw)
    }
    fun signUp(view:View){
        Toast.makeText(view.context, "Registration Successfull please login after the approval",
            Toast.LENGTH_SHORT).show()
        val intent = Intent(view.context, LoginActivity::class.java)
        view.context.startActivity(intent)
    }

}