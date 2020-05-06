package com.myproject.bananaz.di

import com.myproject.bananaz.view.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingsModule {

    @ContributesAndroidInjector
    abstract fun provideLoginActivity():LoginActivity
}