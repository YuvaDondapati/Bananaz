package com.myproject.bananaz.di

import android.content.Context
import com.myproject.bananaz.repository.UserRepository
import com.myproject.bananaz.viewmodel.LoginViewModel
import dagger.BindsInstance
import dagger.Component

@Component(modules =[ApiModule::class])
interface LoginComponent {
    fun inject(repository: UserRepository)
    fun injectVM(viewModel: LoginViewModel)


    @Component.Builder
    interface Builder{

        @BindsInstance
        fun baseUrl(baseUrl:String) : Builder

        fun build(): LoginComponent
    }
}