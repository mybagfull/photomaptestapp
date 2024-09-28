package com.denishrynkevich.photomaptestapp.di.modules

import com.denishrynkevich.photomaptestapp.data.repositoriesImpl.PhotosRepositoryImpl
import com.denishrynkevich.photomaptestapp.data.repositoriesImpl.UserRepositoryImpl
import com.denishrynkevich.photomaptestapp.domain.repositories.PhotosRepository
import com.denishrynkevich.photomaptestapp.domain.repositories.UserRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {

    @Binds
    @Singleton
    fun getUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    fun getPhotosRepository(impl: PhotosRepositoryImpl): PhotosRepository
}