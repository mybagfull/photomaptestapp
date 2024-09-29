package com.denishrynkevich.photomaptestapp.di.modules


import com.denishrynkevich.photomaptestapp.data.repositoriesImpl.CommentsRepositoryImpl
import com.denishrynkevich.photomaptestapp.data.repositoriesImpl.PhotosRepositoryImpl
import com.denishrynkevich.photomaptestapp.data.repositoriesImpl.UserRepositoryImpl
import com.denishrynkevich.photomaptestapp.domain.repositories.CommentsRepository
import com.denishrynkevich.photomaptestapp.domain.repositories.PhotosRepository
import com.denishrynkevich.photomaptestapp.domain.repositories.UserRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {

    @Binds
    @Singleton
    abstract fun getUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun getImageRepository(impl: PhotosRepositoryImpl): PhotosRepository

    @Binds
    @Singleton
    abstract fun getCommentRepository(impl: CommentsRepositoryImpl): CommentsRepository
}
