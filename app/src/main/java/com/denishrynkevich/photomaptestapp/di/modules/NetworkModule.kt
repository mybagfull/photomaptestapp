package com.denishrynkevich.photomaptestapp.di.modules

import com.denishrynkevich.photomaptestapp.data.network.service.AuthService
import com.denishrynkevich.photomaptestapp.data.network.service.CommentService
import com.denishrynkevich.photomaptestapp.data.network.service.ImageService
import com.denishrynkevich.photomaptestapp.data.source.TokenDataSource
import com.denishrynkevich.photomaptestapp.domain.model.ErrorData
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun getRetrofit(moshi: Moshi): Retrofit.Builder {
        return Retrofit.Builder().baseUrl("https://junior.balinasoft.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
    }

    @Provides
    @Singleton
    fun getClient(
        headInterceptor: HeaderInterceptor,
        ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(headInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(tokenManager: TokenDataSource): HeaderInterceptor =
        HeaderInterceptor(tokenManager)


    @Provides
    @Singleton
    fun provideErrorAdapter(moshi: Moshi): JsonAdapter<ErrorData> {
        return moshi.adapter(ErrorData::class.java)
    }

    @Provides
    @Singleton
    fun getMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun getAuthService(retrofit: Retrofit.Builder): AuthService =
        retrofit.build().create(AuthService::class.java)

    @Provides
    @Singleton
    fun getImageService(retrofit: Retrofit.Builder, okHttpClient: OkHttpClient): ImageService =
        retrofit.client(okHttpClient).build().create(ImageService::class.java)

    @Provides
    @Singleton
    fun getCommentService(retrofit: Retrofit.Builder, okHttpClient: OkHttpClient): CommentService =
        retrofit.client(okHttpClient).build().create(CommentService::class.java)
}
