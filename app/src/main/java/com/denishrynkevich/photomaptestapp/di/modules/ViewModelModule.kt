package com.denishrynkevich.photomaptestapp.di.modules

import androidx.lifecycle.ViewModel
import com.denishrynkevich.photomaptestapp.di.viewModel.ViewModelKey
import com.denishrynkevich.photomaptestapp.ui.auth.AuthViewModel
//import com.denishrynkevich.photomaptestapp.ui.photos.PhotosViewModel
import com.denishrynkevich.photomaptestapp.ui.token.TokenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TokenViewModel::class)
    fun bindTokenViewModel(viewModel: TokenViewModel): ViewModel

}