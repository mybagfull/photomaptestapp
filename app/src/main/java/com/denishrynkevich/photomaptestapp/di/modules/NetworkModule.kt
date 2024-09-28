package com.denishrynkevich.photomaptestapp.di.modules

import com.denishrynkevich.photomaptestapp.data.network.AuthService
import com.denishrynkevich.photomaptestapp.domain.model.ErrorData
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun getRetrofit(moshi: Moshi) : Retrofit.Builder {
        return Retrofit.Builder().baseUrl("https://junior.balinasoft.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
    }

    @Provides
    @Singleton
    fun provideErrorAdapter(moshi: Moshi) : JsonAdapter<ErrorData> {
        return moshi.adapter(ErrorData::class.java)
    }

    @Provides
    @Singleton
    fun getMoshi() : Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun getAuthService(retrofit: Retrofit.Builder): AuthService =
        retrofit.build().create(AuthService::class.java)

}