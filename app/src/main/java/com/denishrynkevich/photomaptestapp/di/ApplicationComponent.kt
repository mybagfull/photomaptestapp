package com.denishrynkevich.photomaptestapp.di

import android.content.Context
import com.denishrynkevich.photomaptestapp.MainActivity
import com.denishrynkevich.photomaptestapp.di.modules.DataModule
import com.denishrynkevich.photomaptestapp.di.modules.NetworkModule
import com.denishrynkevich.photomaptestapp.di.modules.SourceModule
import com.denishrynkevich.photomaptestapp.di.modules.ViewModelModule
import com.denishrynkevich.photomaptestapp.ui.auth.AuthActivity
import com.denishrynkevich.photomaptestapp.ui.auth.login.LoginFragment
import com.denishrynkevich.photomaptestapp.ui.auth.register.RegisterFragment
import com.denishrynkevich.photomaptestapp.ui.map.MapFragment
import com.denishrynkevich.photomaptestapp.ui.photos.PhotosFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, NetworkModule::class, SourceModule::class, DataModule::class])
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun inject(activity: MainActivity)
    fun inject(activity: AuthActivity)
    //fun inject(fragment: MapFragment)
    //fun inject(fragment: PhotosFragment)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: RegisterFragment)
}