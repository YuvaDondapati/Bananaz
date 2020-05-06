package com.myproject.bananaz.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.myproject.bananaz.R
import com.myproject.bananaz.databinding.ActivitySignupBinding
import com.myproject.bananaz.viewmodel.SignUpViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import yuvadon.demos.countries.util.isEmailValid
import yuvadon.demos.countries.util.isInvalidMobile

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding
    // 1. Create the view model
    private val signUpViewModel: SignUpViewModel by lazy {
        ViewModelProviders.of(this).get(SignUpViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBindings()
        observeViewModel()
        link_login.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
    fun setUpBindings(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        binding.viewModel = signUpViewModel
        binding.lifecycleOwner = this
    }
    fun observeViewModel(){

        signUpViewModel.emailAddress.observe(this, Observer {
            if(!isEmailValid(it)){
                binding.email.error = getString(R.string.valid_email)
            }
        })
        signUpViewModel.mobileNumber.observe(this, Observer {
            if(isInvalidMobile(it)){
                binding.mobileNumber.error = getString(R.string.valid_Mobile)
            }
        })
    signUpViewModel.cnfmPw.observe(this, Observer {
        if(!signUpViewModel.passwordsMatch(it)){
            binding.cnfmPasswordError.visibility = View.VISIBLE
        }else{
            binding.cnfmPasswordError.visibility = View.GONE
        }
    })

    }
}
