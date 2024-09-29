package com.denishrynkevich.photomaptestapp.di.modules

import androidx.lifecycle.ViewModel
import com.denishrynkevich.photomaptestapp.di.viewModel.ViewModelKey
import com.denishrynkevich.photomaptestapp.ui.auth.AuthViewModel
import com.denishrynkevich.photomaptestapp.ui.token.TokenViewModel
import com.denishrynkevich.photomaptestapp.ui.camera.CameraViewModel
import com.denishrynkevich.photomaptestapp.ui.comments.CommentsViewModel
import com.denishrynkevich.photomaptestapp.ui.map.MapViewModel
import com.denishrynkevich.photomaptestapp.ui.photos.PhotoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PhotoViewModel::class)
    fun bindPhotoViewModel(viewModel: PhotoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    fun bindMapViewModel(viewModel: MapViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TokenViewModel::class)
    fun bindTokenViewModel(viewModel: TokenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CameraViewModel::class)
    fun bindCameraViewModel(viewModel: CameraViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CommentsViewModel::class)
    fun bindCommentViewModel(viewModel: CommentsViewModel): ViewModel
}
