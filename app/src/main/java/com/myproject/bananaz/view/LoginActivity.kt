package com.myproject.bananaz.view

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.myproject.bananaz.R
import com.myproject.bananaz.databinding.ActivityLoginBinding
import com.myproject.bananaz.viewmodel.LoginViewModel
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Flowable
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager


    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBindings()
        observeViewModel()
        link_signup.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }

        requestManager.load(logo).into(binding.logo)
    }
    

    private fun setUpBindings(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = loginViewModel
        binding.lifecycleOwner = this
    }
   private fun observeViewModel(){
        loginViewModel.isEmail.observe(this, Observer {
            if(!it){
                binding.inputEmail.error = getString(R.string.valid_email)
            }
            //    isEmail -> isEmail?.let {emailerror.visibility = if(it) View.GONE else View.VISIBLE  }
        })
        loginViewModel.isPassword.observe(this, Observer {
//                isPassword -> isPassword?.let {passwordError.visibility = if(it) View.GONE else View.VISIBLE  }
            if(!it){
                binding.inputPassword.error = getString(R.string.valid_password)
            }
        })

        loginViewModel.isLoading.observe(this, Observer {
            isLoading -> isLoading?.let { loading_bar.visibility = if (it) View.VISIBLE else View.GONE
        }
        })
        loginViewModel.loginResponse.observe(this, Observer {
           response -> Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

        })
    }
}
