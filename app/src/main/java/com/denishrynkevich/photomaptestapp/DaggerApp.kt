package com.denishrynkevich.photomaptestapp

import android.app.Application
import com.denishrynkevich.photomaptestapp.di.ApplicationComponent
import com.denishrynkevich.photomaptestapp.di.DaggerApplicationComponent

class DaggerApp : Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }
}