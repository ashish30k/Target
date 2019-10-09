package com.ashish.android.myapplication.common.dagger

import com.ashish.android.myapplication.data.TargetAPIService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "http://target-deals.herokuapp.com/"

@Module
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()

    }

    @Provides
    @Singleton
    fun provideTargetApi(retrofit: Retrofit): TargetAPIService {
        return retrofit.create(TargetAPIService::class.java)
    }
}