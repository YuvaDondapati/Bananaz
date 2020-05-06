package com.myproject.bananaz.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.myproject.bananaz.R
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRequestOptions(): RequestOptions{
        return  RequestOptions
            .placeholderOf(R.drawable.no_image)
            .error(R.drawable.no_image)
    }

    @Singleton
    @Provides
    fun provideGlide(app:Application, requestOptions: RequestOptions):RequestManager{
        return Glide.with(app)
            .setDefaultRequestOptions(requestOptions)
    }

    @Provides
    fun provideLogo(app:Application):Drawable{
        return ContextCompat.getDrawable(app, R.drawable.app_logo)!!
    }
}